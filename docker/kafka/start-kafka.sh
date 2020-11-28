#!/usr/bin/env sh

set -e

if [ -z "$ZOOKEEPER_CONNECT" ]; then
  echo Missing ZOOKEEPER_CONNECT environment variable
  exit 1
fi

IP=$(hostname --ip-address)

sed -i "s/^#listeners=.*/listeners=PLAINTEXT:\/\/$IP:9092/g" config/server.properties
sed -i "s/^zookeeper\.connect=.*/zookeeper.connect=$ZOOKEEPER_CONNECT/g" config/server.properties
exec ./bin/kafka-server-start.sh config/server.properties
