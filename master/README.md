# Genitor Master

Genitor Master compiles catalogs to send them to agent.

It needs a PostgreSQL database to save events, and a Kafka to communicate with agents.

## How to build

```bash
./gradlew :genitor-master:assemble
```

## How to run

```bash
docker-compose up -d
./gradlew :genitor-master:bootRun
```

## Documentation
### Configuration
You can configure master by:
- creating YAML or properties file (you need to specify its location with JVM option `spring.config.additional-location`, example: `-Dspring.config.additional-location=/etc/genitor/genitor.yaml`);
- defining JVM options (example: `-Dgenitor.hostname=myhostname`);
- specifying command line arguments as long option (example: `--genitor.hostname=myhostname`;
- defining environment variable.

|                Property               |          Environment variable         |                   Description                   |  Default value |
|:-------------------------------------:|:-------------------------------------:|:-----------------------------------------------:|:--------------:|
|           genitor.deploy-dir          |           GENITOR_DEPLOY_DIR          |             Path to deploy directory            |     deploy     |
|      genitor.server.bind-address      |      GENITOR_SERVER_BIND_ADDRESS      |               Server bind address               |    127.0.0.1   |
|        genitor.server.bind-port       |        GENITOR_SERVER_BIND_PORT       |                 Server bind port                |      2001      |
|            genitor.db.host            |            GENITOR_DB_HOST            |           Host of PostgreSQL database           |    127.0.0.1   |
|            genitor.db.port            |            GENITOR_DB_PORT            |           Port of PostgreSQL database           |      5432      |
|            genitor.db.name            |            GENITOR_DB_NAME            |           Name of PostgreSQL database           | genitor_master |
|          genitor.db.username          |          GENITOR_DB_USERNAME          | Username used to connect to PostgreSQL database | genitor_master |
|          genitor.db.password          |          GENITOR_DB_PASSWORD          | Password used to connect to PostgreSQL database | genitor_master |
|     genitor.kafka.facts-topic.name    |     GENITOR_KAFKA_FACTS_TOPIC_NAME    |               Name of facts topic               |      facts     |
|  genitor.kafka.facts-topic.partitions |  GENITOR_KAFKA_FACTS_TOPIC_PARTITIONS |       Number of partitions of facts topic       |        1       |
|   genitor.kafka.facts-topic.replicas  |   GENITOR_KAFKA_FACTS_TOPIC_REPLICAS  |        Number of replicas of facts topic        |        1       |
|       genitor.kafka.ssl.keystore      |       GENITOR_KAFKA_SSL_KEYSTORE      |              Path to Kafka keystore             |        -       |
|  genitor.kafka.ssl.keystore-password  |  GENITOR_KAFKA_SSL_KEYSTORE_PASSWORD  |            Password of Kafka keystore           |        -       |
|      genitor.kafka.ssl.truststore     |      GENITOR_KAFKA_SSL_TRUSTSTORE     |             Path to Kafka truststore            |        -       |
| genitor.kafka.ssl.truststore-password | GENITOR_KAFKA_SSL_TRUSTSTORE_PASSWORD |           Password of Kafka truststore          |        -       |
