FROM ubuntu:16.04

MAINTAINER Erwin Alberto <erwin@erwindev.com>

ENV PRESTO_HOME /opt/presto
ENV PRESTO_DATA_DIR /var/presto/data

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
RUN wget https://repo1.maven.org/maven2/com/facebook/presto/presto-server/0.193/presto-server-0.193.tar.gz && \
  tar -xzf presto-server-0.193.tar.gz && \
  mv presto-server-0.193 $PRESTO_HOME && \
  rm presto-server-0.193.tar.gz && \
  mkdir -p $PRESTO_DATA_DIR && \
  mkdir $PRESTO_HOME/etc && \
  mkdir $PRESTO_HOME/etc/catalog && \
  wget https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.193/presto-cli-0.193-executable.jar

ADD configs/node.properties $PRESTO_HOME/etc
ADD configs/jvm.config $PRESTO_HOME/etc
ADD configs/log.properties $PRESTO_HOME/etc
ADD configs/config.properties $PRESTO_HOME/etc
ADD configs/jmx.properties $PRESTO_HOME/etc/catalog
ADD configs/hive.properties $PRESTO_HOME/etc/catalog

ADD start-presto.sh start-presto.sh

CMD bash start-presto.sh