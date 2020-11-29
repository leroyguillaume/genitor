# Genitor Master
Genitor compiles catalogs to send them to agent.

## How to build
```bash
./gradlew assemble
```

## How to run
```bash
./gradlew bootRun
```

## Documentation
### Configuration
You can configure master by:
- creating YAML or properties file (you need to specify its location with JVM option `spring.config.additional-location`, example: `-Dspring.config.additional-location=/etc/genitor/genitor.yaml`);
- defining JVM options (example: `-Dgenitor.hostname=myhostname`);
- specifying command line arguments as long option (example: `--genitor.hostname=myhostname`;
- defining environment variable.

|                Property               |          Environment variable         |             Description             | Default value |
|:-------------------------------------:|:-------------------------------------:|:-----------------------------------:|:-------------:|
|     genitor.kafka.facts-topic.name    |     GENITOR_KAFKA_FACTS_TOPIC_NAME    |         Name of facts topic         |     facts     |
|  genitor.kafka.facts-topic.partitions |  GENITOR_KAFKA_FACTS_TOPIC_PARTITIONS | Number of partitions of facts topic |       1       |
|   genitor.kafka.facts-topic.replicas  |   GENITOR_KAFKA_FACTS_TOPIC_REPLICAS  |  Number of replicas of facts topic  |       1       |
|       genitor.kafka.ssl.keystore      |       GENITOR_KAFKA_SSL_KEYSTORE      |        Path to Kafka keystore       |       -       |
|  genitor.kafka.ssl.keystore-password  |  GENITOR_KAFKA_SSL_KEYSTORE_PASSWORD  |      Password of Kafka keystore     |       -       |
|      genitor.kafka.ssl.truststore     |      GENITOR_KAFKA_SSL_TRUSTSTORE     |       Path to Kafka truststore      |       -       |
| genitor.kafka.ssl.truststore-password | GENITOR_KAFKA_SSL_TRUSTSTORE_PASSWORD |     Password of Kafka truststore    |       -       |
