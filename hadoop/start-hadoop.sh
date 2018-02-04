#!/bin/bash

# start ssh server
/etc/init.d/ssh start

# format namenode
$HADOOP_HOME/bin/hdfs namenode -format

# start hadoop
$HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver

$HADOOP_HOME/bin/hdfs dfs -mkdir /user
$HADOOP_HOME/bin/hdfs dfs -mkdir /user/erwinalberto
$HADOOP_HOME/bin/hdfs dfs -chown -R erwinalberto /user
$HADOOP_HOME/bin/hdfs dfs -chmod -R 777 /user

# keep container running
tail -f /dev/null