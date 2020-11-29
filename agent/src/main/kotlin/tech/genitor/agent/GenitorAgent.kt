package tech.genitor.agent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class GenitorAgent

fun main(args: Array<String>) {
    runApplication<GenitorAgent>(*args)
}
