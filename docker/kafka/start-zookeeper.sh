#!/usr/bin/env sh

ZOOKEEPER_CONFIG="$CONFIG_DIR/zookeeper.properties"
JAAS_CONFIG="$CONFIG_DIR/zookeeper_jaas.conf"

echo "Server {
       org.apache.zookeeper.server.auth.DigestLoginModule required
       user_super=\"$ZOOKEEPER_ADMIN_PASSWORD\"
       user_$ZOOKEEPER_KAFKA_USERNAME=\"$ZOOKEEPER_KAFKA_PASSWORD\";
};" >"$JAAS_CONFIG"

export KAFKA_OPTS="-Djava.security.auth.login.config=$JAAS_CONFIG"
exec ./bin/zookeeper-server-start.sh "$ZOOKEEPER_CONFIG"
