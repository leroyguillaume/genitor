package tech.genitor.master

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

/**
 * Kafka topics configuration.
 *
 * @param props Properties.
 */
@Configuration
class KafkaTopicsConfiguration(
    private val props: MasterProperties
) {
    /**
     * Get information about catalog topic creation.
     *
     * @return Information about catalog topic creation.
     */
    @Bean
    fun catalogTopic(): NewTopic = props.kafka.catalogTopic.toNewTopic()

    /**
     * Get information about facts topic creation.
     *
     * @return Information about facts topic creation.
     */
    @Bean
    fun factsTopic(): NewTopic = props.kafka.factsTopic.toNewTopic()

    /**
     * Get topic creation information.
     *
     * @return Topic creation information.
     */
    private fun MasterProperties.Kafka.Topic.toNewTopic() = TopicBuilder
        .name(name)
        .partitions(partitions)
        .replicas(replicas)
        .build()
}
