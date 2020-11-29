#!/bin/python3
#
# This script is used to generate markdown table for available configuration properties.
# It must be executed from the repository root directory.
#
# Usage: ./utils/generate-markdown-conf-table.py <project1> <project2> ...

import re
import sys
from typing import TextIO, Dict, Hashable, Any, Optional, List

import yaml

PROPERTY_VALUE_PATTERN = re.compile("\\${([A-Z_]+)(:[A-Za-z0-9._/-]+)?}")
KDOC_PARAM_PATTERN = re.compile("^\\* @param ([A-Za-z]+) (.*)$")
CAMEL_CASE_PATTERN = re.compile("(?<!^)(?=[A-Z])")


class Property:
    name: str
    default: Optional[str]
    env_var_name: Optional[str]

    def __init__(self, name: str, default: Optional[str], env_var_name: Optional[str] = None):
        self.name = name
        self.default = default
        self.env_var_name = env_var_name


class RowEntry:
    value: Optional[str]
    length: int

    def __init__(self, value: Optional[str], length: int):
        self.value = value
        self.length = length


def _display_table(properties: List[Property], kdoc_params: Dict[str, str]):
    property_header = "Property"
    env_var_header = "Environment variable"
    desc_header = "Description"
    default_header = "Default value"
    headers = {
        property_header: len(max([prop.name for prop in properties] + [property_header], key=len)),
        env_var_header: len(
            max([prop.env_var_name for prop in properties if prop.env_var_name is not None] + [env_var_header], key=len)
        ),
        desc_header: len(max([desc for desc in kdoc_params.values()] + [desc_header], key=len)),
        default_header: len(
            max([prop.default for prop in properties if prop.default is not None] + [default_header], key=len)
        )
    }
    columns_len = [length for header, length in headers.items()]
    _table_headers(headers)
    for prop in properties:
        desc = [desc for param, desc in kdoc_params.items() if prop.name.endswith(param)][0]
        _table_prop_row(prop, desc, columns_len)


def _center(value: str, max_len: int):
    blank_nb = max_len - len(value)
    if blank_nb % 2 == 0:
        left_blank = _repeat_string(" ", int(blank_nb / 2))
        right_blank = left_blank
    else:
        left_blank = _repeat_string(" ", int(blank_nb / 2 + 1))
        right_blank = _repeat_string(" ", int(blank_nb / 2))
    return left_blank + value + right_blank


def _flat(properties: Dict[Hashable, Any], parent: Optional[str] = None) -> List[Property]:
    flatten_properties = []
    for property_name, property_value in properties.items():
        if parent is None:
            flatten_property_name = property_name
        else:
            flatten_property_name = parent + '.' + property_name
        if flatten_property_name.startswith('genitor'):
            if type(property_value) is dict:
                flatten_properties += _flat(property_value, flatten_property_name)
            elif type(property_value) is str:
                matcher = PROPERTY_VALUE_PATTERN.match(property_value)
                if matcher is None:
                    flatten_properties.append(Property(flatten_property_name, property_value))
                else:
                    groups = matcher.groups()
                    env_var_name = groups[0]
                    default_value = groups[1]
                    if default_value is None:
                        flatten_properties.append(Property(flatten_property_name, None, env_var_name))
                    else:
                        flatten_properties.append(Property(flatten_property_name, default_value[1:], env_var_name))
            else:
                flatten_properties.append(Property(flatten_property_name, str(property_value)))
    return flatten_properties


def _kebab_case(var: str) -> str:
    return CAMEL_CASE_PATTERN.sub('-', var).lower()


def _kdoc_params(file: TextIO) -> Dict[str, str]:
    kdoc_params = {}
    for line in file.readlines():
        matcher = KDOC_PARAM_PATTERN.match(line.strip())
        if matcher is not None:
            kdoc_params[_kebab_case(matcher.group(1))] = matcher.group(2)
    return kdoc_params


def _repeat_string(string: str, nb: int) -> str:
    blank_str = ''
    for i in range(0, nb):
        blank_str += string
    return blank_str


def _table_headers(headers: Dict[str, int]):
    dash_string = "|:"
    for header, length in headers.items():
        dash_string += _repeat_string("-", length) + ":|:"
    headers_entries = [RowEntry(value, length) for value, length in headers.items()]
    _table_row(headers_entries)
    print(dash_string[:-1])


def _table_prop_row(prop: Property, desc: str, columns_len: List[int]):
    _table_row(
        [
            RowEntry(prop.name, columns_len[0]),
            RowEntry(prop.env_var_name, columns_len[1]),
            RowEntry(desc, columns_len[2]),
            RowEntry(prop.default, columns_len[3])
        ]
    )


def _table_row(row: List[RowEntry]):
    row_string = "| "
    for entry in row:
        if entry.value is None:
            value = '-'
        else:
            value = entry.value
        row_string += _center(value, entry.length) + " | "
    print(row_string[:-1])


def main():
    for project_name in sys.argv[1:]:
        print(project_name)
        application_yaml_filepath = project_name + "/src/main/resources/application.yml"
        class_filepath = project_name + "/src/main/kotlin/tech/genitor/" + project_name + "/GenitorProperties.kt"

        with (open(application_yaml_filepath)) as application_yaml_file:
            properties = _flat(yaml.load(application_yaml_file, Loader=yaml.FullLoader))
        with (open(class_filepath)) as class_file:
            kdoc_params = _kdoc_params(class_file)
        _display_table(properties, kdoc_params)
        print()


if __name__ == '__main__':
    main()
