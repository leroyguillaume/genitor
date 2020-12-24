FROM openjdk:11-jre

ARG version

ENV GENITOR_VERSION $version

RUN mkdir /opt/genitor
ADD genitor-master-$GENITOR_VERSION.tar.gz /opt/genitor
WORKDIR /opt/genitor/genitor-master-$GENITOR_VERSION
ENTRYPOINT ./bin/genitor-master
EXPOSE 2001
