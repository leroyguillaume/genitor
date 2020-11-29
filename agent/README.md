# Genitor Agent
Execute catalogs on a machine.

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
You can configure agent by:
- creating YAML or properties file (you need to specify its location with JVM option `spring.config.additional-location`, example: `-Dspring.config.additional-location=/etc/genitor/genitor.yaml`);
- defining JVM options (example: `-Dgenitor.hostname=myhostname`);
- specifying command line arguments as long option (example: `--genitor.hostname=myhostname`;
- defining environment variable.

|                Property               |          Environment variable         |          Description         | Default value |
|:-------------------------------------:|:-------------------------------------:|:----------------------------:|:-------------:|
|            genitor.hostname           |            GENITOR_HOSTNAME           |     Hostname of this node    |  ${HOSTNAME}  |
|       genitor.kafka.facts-topic       |     GENITOR_KAFKA_FACTS_TOPIC_NAME    |      Name of facts topic     |     facts     |
|       genitor.kafka.ssl.keystore      |       GENITOR_KAFKA_SSL_KEYSTORE      |    Path to Kafka keystore    |       -       |
|  genitor.kafka.ssl.keystore-password  |  GENITOR_KAFKA_SSL_KEYSTORE_PASSWORD  |  Password of Kafka keystore  |       -       |
|      genitor.kafka.ssl.truststore     |      GENITOR_KAFKA_SSL_TRUSTSTORE     |   Path to Kafka truststore   |       -       |
| genitor.kafka.ssl.truststore-password | GENITOR_KAFKA_SSL_TRUSTSTORE_PASSWORD | Password of Kafka truststore |       -       |
