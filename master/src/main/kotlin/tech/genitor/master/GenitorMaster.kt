package tech.genitor.master

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class GenitorMaster

fun main(args: Array<String>) {
    runApplication<GenitorMaster>(*args)
}
