#!/bin/bash

# start ssh server
/etc/init.d/ssh start

# format namenode
$HADOOP_HOME/bin/hdfs namenode -format

# start hadoop
$HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver

# create data folders
$HADOOP_HOME/bin/hdfs dfs -mkdir /user
$HADOOP_HOME/bin/hdfs dfs -mkdir /user/erwinalberto
$HADOOP_HOME/bin/hdfs dfs -mkdir /user/erwinalberto/openpayments
$HADOOP_HOME/bin/hdfs dfs -chown -R erwinalberto /user
$HADOOP_HOME/bin/hdfs dfs -chmod -R 777 /user

# create hive folders
$HADOOP_HOME/bin/hdfs dfs -mkdir /user/hive
$HADOOP_HOME/bin/hdfs dfs -mkdir /user/hive/warehouse
$HADOOP_HOME/bin/hdfs dfs -chmod g+w /tmp
$HADOOP_HOME/bin/hdfs dfs -chmod g+w /user/hive/warehouse

# run the schema tools
$HIVE_HOME/bin/schematool -dbType derby -initSchema
$HIVE_HOME/bin/hive -f /opt/hive/conf/hive_init.sql
$HIVE_HOME/bin/hive --service metastore


# keep container running
tail -f /dev/null