# This docker image is only for developpment, please don't use it in production
FROM openjdk:11-jre AS kafka_base

ARG SCALA_VERSION=2.13
ARG KAFKA_VERSION=2.6.0

ENV SCALA_VERSION $SCALA_VERSION
ENV KAFKA_VERSION $KAFKA_VERSION

RUN curl https://downloads.apache.org/kafka/$KAFKA_VERSION/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz > /opt/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz && \
    tar -xzf /opt/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz -C /opt
WORKDIR /opt/kafka_$SCALA_VERSION-$KAFKA_VERSION

FROM kafka_base AS zookeeper
ENTRYPOINT ./bin/zookeeper-server-start.sh config/zookeeper.properties
EXPOSE 2181

FROM kafka_base AS kafka
COPY start-kafka.sh .
RUN chmod u+x start-kafka.sh
ENTRYPOINT ./start-kafka.sh
EXPOSE 9092
