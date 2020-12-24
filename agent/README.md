# Genitor Agent

Execute catalogs on a machine.

It needs a Kafka to communicate with master.

## How to build

```bash
./gradlew :genitor-agent:assemble
```

## How to run

```bash
docker-compose up -d
./gradlew :genitor-agent:bootRun
```

## Documentation
### Configuration
You can configure agent by:
- creating YAML or properties file (you need to specify its location with JVM option `spring.config.additional-location`, example: `-Dspring.config.additional-location=/etc/genitor/genitor.yaml`);
- defining JVM options (example: `-Dgenitor.hostname=myhostname`);
- specifying command line arguments as long option (example: `--genitor.hostname=myhostname`;
- defining environment variable.

|                Property               |          Environment variable         |                   Description                   |  Default value |
|:-------------------------------------:|:-------------------------------------:|:-----------------------------------------------:|:--------------:|
|            genitor.hostname           |            GENITOR_HOSTNAME           |              Hostname of this node              |        -       |
|      genitor.collect-facts-every      |      GENITOR_COLLECT_FACTS_EVERY      | Number of minutes between two facts collection. |        5       |
|      genitor.server.bind-address      |      GENITOR_SERVER_BIND_ADDRESS      |               Server bind address               |    127.0.0.1   |
|        genitor.server.bind-port       |        GENITOR_SERVER_BIND_PORT       |                 Server bind port                |      2010      |
|    genitor.kafka.bootstrap-servers    |    GENITOR_KAFKA_BOOTSTRAP_SERVERS    |        Kafka servers comma-separated list       | localhost:9093 |
|      genitor.kafka.catalog-topic      |    GENITOR_KAFKA_CATALOG_TOPIC_NAME   |              Name of catalog topic              |     catalog    |
|       genitor.kafka.facts-topic       |     GENITOR_KAFKA_FACTS_TOPIC_NAME    |               Name of facts topic               |      facts     |
|       genitor.kafka.ssl.keystore      |       GENITOR_KAFKA_SSL_KEYSTORE      |              Path to Kafka keystore             |        -       |
|  genitor.kafka.ssl.keystore-password  |  GENITOR_KAFKA_SSL_KEYSTORE_PASSWORD  |            Password of Kafka keystore           |        -       |
|      genitor.kafka.ssl.truststore     |      GENITOR_KAFKA_SSL_TRUSTSTORE     |             Path to Kafka truststore            |        -       |
| genitor.kafka.ssl.truststore-password | GENITOR_KAFKA_SSL_TRUSTSTORE_PASSWORD |           Password of Kafka truststore          |        -       |

If `genitor.hostname` is blank, machine hostname is taken.
