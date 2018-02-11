##########################
#
# NOT FOR PRODUCTION USE
#
##########################
FROM ubuntu:16.04

MAINTAINER Erwin Alberto <erwin@erwindev.com>

ENV ZEPPELIN_HOME /opt/zeppelin

# install java + others
RUN apt-get update && apt-get install -y \
  wget \
  vim \
  openjdk-8-jdk \
  iputils-ping

# install kafka
RUN wget http://mirrors.koehn.com/apache/zeppelin/zeppelin-0.7.3/zeppelin-0.7.3-bin-all.tgz && \
  tar -xzf zeppelin-0.7.3-bin-all.tgz && \
  mv zeppelin-0.7.3-bin-all $ZEPPELIN_HOME && \
  rm zeppelin-0.7.3-bin-all.tgz

EXPOSE 8082

ADD configs/zeppelin-site.xml $ZEPPELIN_HOME/conf/zeppelin-site.xml
ADD configs/zeppelin-env.sh $ZEPPELIN_HOME/conf/zeppelin-env.sh
# ADD configs/presto-jdbc-0.194.jar $ZEPPELIN_HOME/interpreter/jdbc/presto-jdbc-0.194.jar

ADD start-zeppelin.sh start-zeppelin.sh

CMD bash start-zeppelin.sh