# Hadoop, Kafka and Presto

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

Update hosts file
```
localhost   hadoop.erwin.com kafka.erwin.com presto.erwin.com
```

Run Hadoop, Kafka and Presto
```
$ docker-compose up -d
```

Download and run the [Presto CLI](https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.194/presto-cli-0.194-executable.jar)
```
$ mv presto-cli-0.194-executable.jar presto
$ chmod +x presto
$ ./presto --server presto.erwin.com:8080 --catalog hive --schema default
``` 
