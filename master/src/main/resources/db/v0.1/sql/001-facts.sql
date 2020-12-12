CREATE TABLE facts
(
    hostname               varchar(255) NOT NULL PRIMARY KEY,
    last_modification_date timestamp    NOT NULL DEFAULT now(),
    version                int          NOT NULL DEFAULT 1,
    facts                  jsonb        NOT NULL
);
