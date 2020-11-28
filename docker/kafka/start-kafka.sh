#!/usr/bin/env sh

set -e

# Check required environment variables
if [ -z "$ZOOKEEPER_CONNECT" ]; then
  echo Missing ZOOKEEPER_CONNECT environment variable
  exit 1
fi

# Define optional environment variables
if [ -z "$KAFKA_HOSTNAME" ]; then
  KAFKA_HOSTNAME=$(hostname --ip-address)
fi

SERVER_CONFIG="$CONFIG_DIR/server.properties"
JAAS_CONFIG="$CONFIG_DIR/kafka_server_jaas.conf"
INIT_FILE='.keepme_config_initialized'

if [ ! -f "$INIT_FILE" ]; then
  # Listeners
  sed -i "s/^#listeners=.*/listeners=SSL:\/\/$KAFKA_HOSTNAME:9093,SASL_SSL:\/\/$KAFKA_HOSTNAME:9094/g" "$SERVER_CONFIG"

  # Zookeeper
  sed -i "s/^zookeeper\.connect=.*/zookeeper.connect=$ZOOKEEPER_CONNECT/g" "$SERVER_CONFIG"

  # SSL
  echo "security.inter.broker.protocol=SSL
  ssl.client.auth=required
  ssl.endpoint.identification.algorithm=
  ssl.truststore.location=$TRUSTSTORE
  ssl.truststore.password=$TRUSTSTORE_PASSWORD
  ssl.keystore.location=$KEYSTORE
  ssl.keystore.password=$KEYSTORE_PASSWORD
  ssl.key.password=$KEYSTORE_PASSWORD
  sasl.enabled.mechanisms=PLAIN" >>"$SERVER_CONFIG"

  echo "KafkaServer {
   org.apache.kafka.common.security.plain.PlainLoginModule required
   username=\"$KAFKA_BROKER_USERNAME\"
   password=\"$KAFKA_BROKER_PASSWORD\"
   user_$KAFKA_BROKER_USERNAME=\"$KAFKA_BROKER_PASSWORD\"
   user_$KAFKA_CLIENT_USERNAME=\"$KAFKA_CLIENT_PASSWORD\";
};

Client {
   org.apache.zookeeper.server.auth.DigestLoginModule required
   username=\"$ZOOKEEPER_KAFKA_USERNAME\"
   password=\"$ZOOKEEPER_KAFKA_PASSWORD\";
};" >"$JAAS_CONFIG"

  touch "$INIT_FILE"
fi

export KAFKA_OPTS="-Djava.security.auth.login.config=$JAAS_CONFIG"
exec ./bin/kafka-server-start.sh "$SERVER_CONFIG"
