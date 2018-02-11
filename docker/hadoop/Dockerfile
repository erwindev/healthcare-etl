##########################
#
# NOT FOR PRODUCTION USE
#
##########################
FROM ubuntu:16.04

MAINTAINER Erwin Alberto <erwin@erwindev.com>

# set environment vars
ENV HADOOP_HOME /opt/hadoop
ENV HADOOP_VERSION 2.9.0
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV HIVE_VERSION 2.3.2
ENV HIVE_HOME /opt/hive
ENV DERBY_HOME /opt/derby
ENV DERBY_VERSION 10.14.1.0
ENV PATH $HIVE_HOME/bin:$DERBY_HOME/bin:$PATH
ENV CLASSPATH $DERBY_HOME/lib/derby.jar:$DERBY_HOME/lib/derbytools.jar

# install packages
RUN \
  apt-get update && apt-get install -y \
  ssh \
  rsync \
  vim \
  openjdk-8-jdk \
  iputils-ping


# Install Hadoop, set JAVA_HOME in hadoop-env.sh, update path
RUN \
  wget http://apache.mirrors.tds.net/hadoop/common/hadoop-$HADOOP_VERSION/hadoop-$HADOOP_VERSION.tar.gz && \
  tar -xzf hadoop-$HADOOP_VERSION.tar.gz && \
  mv hadoop-$HADOOP_VERSION $HADOOP_HOME && \
  rm hadoop-$HADOOP_VERSION.tar.gz && \
  echo "export JAVA_HOME=$JAVA_HOME" >> $HADOOP_HOME/etc/hadoop/hadoop-env.sh && \
  echo "PATH=$PATH:$HADOOP_HOME/bin" >> ~/.bashrc

# copy hadoop configs
ADD configs/*xml $HADOOP_HOME/etc/hadoop/

#Install Hive and Derby
RUN apt-get update && apt-get install -y wget procps && \
	wget http://apache.mirror.digionline.de/hive/hive-$HIVE_VERSION/apache-hive-$HIVE_VERSION-bin.tar.gz && \
	tar -xzvf apache-hive-$HIVE_VERSION-bin.tar.gz && \
	mv apache-hive-$HIVE_VERSION-bin $HIVE_HOME && \
	rm apache-hive-$HIVE_VERSION-bin.tar.gz && \
    wget http://www-us.apache.org/dist/db/derby/db-derby-$DERBY_VERSION/db-derby-$DERBY_VERSION-bin.tar.gz && \
    tar xvzf db-derby-$DERBY_VERSION-bin.tar.gz && \
    mv db-derby-$DERBY_VERSION-bin $DERBY_HOME && \
    mkdir $DERBY_HOME/data && \
	rm db-derby-$DERBY_VERSION-bin.tar.gz && \
	apt-get --purge remove -y wget && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/*

# copy hive configs
ADD configs/hive-env.sh $HIVE_HOME/conf
ADD configs/hive-site.xml $HIVE_HOME/conf
ADD configs/jpox.properties $HIVE_HOME/conf
ADD configs/hive_init.sql $HIVE_HOME/conf


# create ssh keys
RUN \
  ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa && \
  cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys && \
  chmod 0600 ~/.ssh/authorized_keys


# copy ssh config
ADD configs/ssh_config /root/.ssh/config

# copy script to start hadoop
ADD start-hadoop.sh start-hadoop.sh

# expose various ports
EXPOSE 8088 9000 9083 50070 50075 50030 50060 50010 10000

# start hadoop
CMD bash start-hadoop.sh