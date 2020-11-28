#!/usr/bin/env sh

if [ -z "$ZOOKEEPER_CONNECT" ]; then
  echo Missing ZOOKEEPER_CONNECT environment variable
  exit 1
fi

sed -i "s/^zookeeper\.connect=.*/zookeeper.connect=$ZOOKEEPER_CONNECT/g" config/server.properties
exec ./bin/kafka-server-start.sh config/server.properties
