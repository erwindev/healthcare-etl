FROM ubuntu:16.04

MAINTAINER Erwin Alberto <erwin@erwindev.com>

ENV KAFKA_HOME /opt/kafka

# install java + others
RUN apt-get update && apt-get install -y \
  wget \
  vim \
  openjdk-8-jdk \
  iputils-ping

# install kafka
RUN wget http://apache.osuosl.org/kafka/1.0.0/kafka_2.12-1.0.0.tgz && \
  tar -xzf kafka_2.12-1.0.0.tgz && \
  mv kafka_2.12-1.0.0 $KAFKA_HOME && \
  rm kafka_2.12-1.0.0.tgz

EXPOSE 2181 9092

ADD start-kafka.sh start-kafka.sh

CMD bash start-kafka.sh