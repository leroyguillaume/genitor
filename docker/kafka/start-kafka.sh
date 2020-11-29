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
INIT_FILE='.keepme_config_initialized'

if [ ! -f "$INIT_FILE" ]; then
  # Listeners
  sed -i "s/^#listeners=.*/listeners=PLAINTEXT:\/\/$KAFKA_HOSTNAME:9092,SSL:\/\/$KAFKA_HOSTNAME:9093/g" "$SERVER_CONFIG"

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
  ssl.key.password=$KEYSTORE_PASSWORD" >>"$SERVER_CONFIG"

  touch "$INIT_FILE"
fi

exec ./bin/kafka-server-start.sh "$SERVER_CONFIG"
