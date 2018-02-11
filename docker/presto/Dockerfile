##########################
#
# NOT FOR PRODUCTION USE
#
##########################
FROM ubuntu:16.04

MAINTAINER Erwin Alberto <erwin@erwindev.com>

ENV PRESTO_HOME /opt/presto
ENV PRESTO_DATA_DIR /var/presto/data
ENV PRESTO_VERSION 0.170

# install java + others
RUN apt-get update && apt-get install -y \
  wget \
  vim \
  openjdk-8-jdk \
  iputils-ping \
  python \
  python-pip \
  python-dev \
  telnet

# install presto
RUN wget https://repo1.maven.org/maven2/com/facebook/presto/presto-server/$PRESTO_VERSION/presto-server-$PRESTO_VERSION.tar.gz && \
  tar -xzf presto-server-$PRESTO_VERSION.tar.gz && \
  mv presto-server-$PRESTO_VERSION $PRESTO_HOME && \
  rm presto-server-$PRESTO_VERSION.tar.gz && \
  mkdir -p $PRESTO_DATA_DIR && \
  mkdir $PRESTO_HOME/etc && \
  mkdir $PRESTO_HOME/etc/catalog && \
  wget https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/$PRESTO_VERSION/presto-cli-$PRESTO_VERSION-executable.jar

EXPOSE 8081

ADD configs/node.properties $PRESTO_HOME/etc
ADD configs/jvm.config $PRESTO_HOME/etc
ADD configs/log.properties $PRESTO_HOME/etc
ADD configs/config.properties $PRESTO_HOME/etc
ADD configs/jmx.properties $PRESTO_HOME/etc/catalog
ADD configs/hive.properties $PRESTO_HOME/etc/catalog

ADD start-presto.sh start-presto.sh

CMD bash start-presto.sh