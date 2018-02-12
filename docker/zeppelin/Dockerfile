##########################
#
# NOT FOR PRODUCTION USE
#
##########################
FROM ubuntu:16.04

MAINTAINER Erwin Alberto <erwin@erwindev.com>

ENV ZEPPELIN_HOME /opt/zeppelin
ENV ZEPPELIN_VERSION 0.7.3

# install java + others
RUN apt-get update && apt-get install -y \
  wget \
  vim \
  openjdk-8-jdk \
  iputils-ping

# install kafka
RUN wget http://mirrors.koehn.com/apache/zeppelin/zeppelin-$ZEPPELIN_VERSION/zeppelin-$ZEPPELIN_VERSION-bin-all.tgz && \
  tar -xzf zeppelin-$ZEPPELIN_VERSION-bin-all.tgz && \
  mv zeppelin-$ZEPPELIN_VERSION-bin-all $ZEPPELIN_HOME && \
  rm zeppelin-$ZEPPELIN_VERSION-bin-all.tgz

EXPOSE 8082

ADD configs/zeppelin-site.xml $ZEPPELIN_HOME/conf/zeppelin-site.xml
ADD configs/zeppelin-env.sh $ZEPPELIN_HOME/conf/zeppelin-env.sh
ADD configs/presto-jdbc-0.170.jar $ZEPPELIN_HOME/interpreter/jdbc/presto-jdbc-0.170.jar

ADD start-zeppelin.sh start-zeppelin.sh

CMD bash start-zeppelin.sh