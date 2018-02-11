# Hadoop, Kafka and Presto

Install Docker
* [MacOSX](https://docs.docker.com/docker-for-mac/install/)
* [Windows](https://docs.docker.com/docker-for-windows/install/)

Build Hadoop
```
$ docker-compose build hadoop
```

Build Kafka
```
$ docker-compose build kafka
```

Build Presto
```
$ docker-compose build presto
```

Build Zeppelin
```
$ docker-compose build zeppelin
```

Update hosts file
```
localhost   hadoop.erwin.com kafka.erwin.com presto.erwin.com zeppelin.erwin.com
```

Run Hadoop, Kafka, Presto and Zeppelin
```
$ docker-compose up -d
```

Download and run the [Presto CLI](https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.194/presto-cli-0.194-executable.jar)
```
$ mv presto-cli-0.194-executable.jar presto
$ chmod +x presto
$ ./presto --server presto.erwin.com:8080 --catalog hive --schema default
``` 

To access Presto UI, click [here](http://presto.erwin.com:8080/)
To access Hadoop UI, click [here](http://hadoop.erwin.com:50070/dfshealth.html#tab-overview)
