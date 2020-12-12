package tech.genitor.master

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * Master application.
 */
@ConfigurationPropertiesScan
@SpringBootApplication
class GenitorMaster

/**
 * Start master.
 *
 * @param args Command line arguments.
 */
fun main(args: Array<String>) {
    runApplication<GenitorMaster>(*args)
}
