#!/usr/bin/env bash

set -e

KEY_EXTENSION='key'
CERT_EXTENSION='cert'
CSR_EXTENSION='csr'
CA_ALIAS='CARoot'
CA_KEY_FILENAME="$CA_ALIAS.$KEY_EXTENSION"
CA_CERT_FILENAME="$CA_ALIAS.$CERT_EXTENSION"
SERVER_ALIAS='server'
SERVER_CERT_FILENAME="$SERVER_ALIAS.$CERT_EXTENSION"
SERVER_CSR_FILENAME="$SERVER_ALIAS.$CSR_EXTENSION"
CLIENT_ALIAS='client'
CLIENT_CERT_FILENAME="$CLIENT_ALIAS.$CERT_EXTENSION"
CLIENT_CSR_FILENAME="$CLIENT_ALIAS.$CSR_EXTENSION"
SERVER_KEYSTORE='kafka.server.keystore.jks'
SERVER_TRUSTSTORE='kafka.server.truststore.jks'
CLIENT_KEYSTORE='kafka.client.keystore.jks'
CLIENT_TRUSTSTORE='kafka.client.truststore.jks'
VALIDITY=9999
PASSWORD='changeit'

# CA certificate
openssl req -new -x509 -keyout "$CA_KEY_FILENAME" -out "$CA_CERT_FILENAME" -days $VALIDITY -subj "/CN=$CA_ALIAS" -passout "pass:$PASSWORD"
keytool -keystore "$SERVER_TRUSTSTORE" -alias "$CA_ALIAS" -importcert -file "$CA_CERT_FILENAME" -storepass "$PASSWORD" -noprompt
keytool -keystore "$CLIENT_TRUSTSTORE" -alias "$CA_ALIAS" -importcert -file "$CA_CERT_FILENAME" -storepass "$PASSWORD" -noprompt
keytool -keystore "$SERVER_KEYSTORE" -alias "$CA_ALIAS" -importcert -file "$CA_CERT_FILENAME" -storepass "$PASSWORD" -noprompt
keytool -keystore "$CLIENT_KEYSTORE" -alias "$CA_ALIAS" -importcert -file "$CA_CERT_FILENAME" -storepass "$PASSWORD" -noprompt

# Kafka server certificate
keytool -keystore "$SERVER_KEYSTORE" -alias "$SERVER_ALIAS" -keyalg RSA -validity $VALIDITY -genkey -storepass "$PASSWORD" -keypass "$PASSWORD" -dname "CN=$SERVER_ALIAS"
keytool -keystore "$SERVER_KEYSTORE" -alias "$SERVER_ALIAS" -certreq -file "$SERVER_CSR_FILENAME" -storepass "$PASSWORD"
openssl x509 -req -CA "$CA_CERT_FILENAME" -CAkey "$CA_KEY_FILENAME" -in "$SERVER_CSR_FILENAME" -out "$SERVER_CERT_FILENAME" -days $VALIDITY -CAcreateserial -passin "pass:$PASSWORD"
keytool -keystore "$SERVER_KEYSTORE" -alias "$SERVER_ALIAS" -importcert -file "$SERVER_CERT_FILENAME" -storepass "$PASSWORD" -noprompt

# Kafka client certificate
keytool -keystore "$CLIENT_KEYSTORE" -alias "$CLIENT_ALIAS" -keyalg RSA -validity $VALIDITY -genkey -storepass "$PASSWORD" -keypass "$PASSWORD" -dname "CN=$CLIENT_ALIAS"
keytool -keystore "$CLIENT_KEYSTORE" -alias "$CLIENT_ALIAS" -certreq -file "$CLIENT_CSR_FILENAME" -storepass "$PASSWORD"
openssl x509 -req -CA "$CA_CERT_FILENAME" -CAkey "$CA_KEY_FILENAME" -in "$CLIENT_CSR_FILENAME" -out "$CLIENT_CERT_FILENAME" -days $VALIDITY -CAcreateserial -passin "pass:$PASSWORD"
keytool -keystore "$CLIENT_KEYSTORE" -alias "$CLIENT_ALIAS" -importcert -file "$CLIENT_CERT_FILENAME" -storepass "$PASSWORD" -noprompt
