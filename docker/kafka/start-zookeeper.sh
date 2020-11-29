#!/usr/bin/env sh

ZOOKEEPER_CONFIG="$CONFIG_DIR/zookeeper.properties"

exec ./bin/zookeeper-server-start.sh "$ZOOKEEPER_CONFIG"
