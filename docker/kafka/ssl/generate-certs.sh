#!/usr/bin/env bash

set -e

KEY_EXTENSION='key'
CERT_EXTENSION='cert'
CSR_EXTENSION='csr'
CA_ALIAS='ca'
CA_KEY_FILENAME="$CA_ALIAS.$KEY_EXTENSION"
CA_CERT_FILENAME="$CA_ALIAS.$CERT_EXTENSION"
ALIAS='localhost'
CERT_FILENAME="$ALIAS.$CERT_EXTENSION"
CSR_FILENAME="$ALIAS.$CSR_EXTENSION"
KEYSTORE='kafka.server.keystore.jks'
SERVER_TRUSTSTORE='kafka.server.truststore.jks'
CLIENT_TRUSTSTORE='kafka.client.truststore.jks'
VALIDITY=9999
PASSWORD='changeit'

keytool -keystore "$KEYSTORE" -alias "$ALIAS" -keyalg RSA -validity $VALIDITY -genkey -storepass "$PASSWORD" -keypass "$PASSWORD" -dname "CN=$CA_ALIAS"
openssl req -new -x509 -keyout "$CA_KEY_FILENAME" -out "$CA_CERT_FILENAME" -days $VALIDITY -subj "/CN=$CA_ALIAS" -passout "pass:$PASSWORD"
keytool -keystore "$CLIENT_TRUSTSTORE" -alias "$CA_ALIAS" -importcert -file "$CA_CERT_FILENAME" -storepass "$PASSWORD" -noprompt
keytool -keystore "$SERVER_TRUSTSTORE" -alias "$CA_ALIAS" -importcert -file "$CA_CERT_FILENAME" -storepass "$PASSWORD" -noprompt
keytool -keystore "$KEYSTORE" -alias localhost -certreq -file "$CSR_FILENAME" -storepass "$PASSWORD"
openssl x509 -req -CA "$CA_CERT_FILENAME" -CAkey "$CA_KEY_FILENAME" -in "$CSR_FILENAME" -out "$CERT_FILENAME" -days $VALIDITY -CAcreateserial -passin "pass:$PASSWORD"
keytool -keystore "$KEYSTORE" -alias "$CA_ALIAS" -importcert -file "$CA_CERT_FILENAME" -storepass "$PASSWORD" -noprompt
keytool -keystore "$KEYSTORE" -alias "$ALIAS" -importcert -file "$CERT_FILENAME" -storepass "$PASSWORD" -noprompt
