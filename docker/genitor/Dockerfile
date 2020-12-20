FROM openjdk:11-jre AS genitor_base

ARG version

ENV GENITOR_VERSION $version

RUN mkdir /opt/genitor

# ======================
# Master
# ======================
FROM genitor_base AS master

ADD genitor-master-$GENITOR_VERSION.tar.gz /opt/genitor
WORKDIR /opt/genitor/genitor-master-$GENITOR_VERSION
ENTRYPOINT ./bin/genitor-master --spring.profiles.active=$GENITOR_PROFILE
EXPOSE 2001

# ======================
# Agent
# ======================
FROM genitor_base AS agent

ADD genitor-agent-$GENITOR_VERSION.tar.gz /opt/genitor
WORKDIR /opt/genitor/genitor-agent-$GENITOR_VERSION
ENTRYPOINT ./bin/genitor-agent --spring.profiles.active=$GENITOR_PROFILE
EXPOSE 2010
