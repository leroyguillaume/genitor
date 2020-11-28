# This docker image is only for developpment, please don't use it in production
FROM openjdk:11-jre AS kafka_base

ARG SCALA_VERSION=2.13
ARG KAFKA_VERSION=2.6.0
ENV SCALA_VERSION $SCALA_VERSION
ENV KAFKA_VERSION $KAFKA_VERSION

ENV CONFIG_DIR='config'
ENV ZOOKEEPER_ADMIN_PASSWORD='changeit'
ENV ZOOKEEPER_KAFKA_USERNAME='kafka'
ENV ZOOKEEPER_KAFKA_PASSWORD='changeit'

RUN curl https://downloads.apache.org/kafka/$KAFKA_VERSION/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz > /opt/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz && \
    tar -xzf /opt/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz -C /opt
WORKDIR /opt/kafka_$SCALA_VERSION-$KAFKA_VERSION

# ======================
# Zookeeper
# ======================
FROM kafka_base AS zookeeper

COPY start-zookeeper.sh .
RUN chmod u+x start-zookeeper.sh
ENTRYPOINT ./start-zookeeper.sh
EXPOSE 2181

# ======================
# Kafka
# ======================
FROM kafka_base AS kafka

ENV KAFKA_BROKER_USERNAME='kafkabroker'
ENV KAFKA_BROKER_PASSWORD='changeit'
ENV KAFKA_CLIENT_USERNAME='client'
ENV KAFKA_CLIENT_PASSWORD='changeit'

ENV KEYSTORE='/etc/ssl/private/kafka.server.keystore.jks'
ENV KEYSTORE_PASSWORD='changeit'
ENV TRUSTSTORE='/etc/ssl/private/kafka.server.truststore.jks'
ENV TRUSTSTORE_PASSWORD='changeit'

COPY start-kafka.sh .
COPY ssl/kafka.server.*.jks /etc/ssl/private/
RUN chmod u+x start-kafka.sh
ENTRYPOINT ./start-kafka.sh
EXPOSE 9093 9094
