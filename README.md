# Realtime Data Pipeline
This is a sample project that demonstrates a realtime data pipeline using Hadoop, Kafka and Presto.  For this project we wil be using the Open Payments Data from Centers fpr Medicare and Medicaid Services.

## Run the application

Clone the project
```
$ git clone https://github.com/erwindev/healthcare-etl.git
$ cd docker
```

In order to run the application, we need to 

Build Hadoop docker image
```
$ docker-compose build hadoop
```

Build Kafka docker image
```
$ docker-compose build kafka
```

Build Presto docker image
```
$ docker-compose build presto
```

Update your hosts file
```
localhost   hadoop.erwin.com kafka.erwin.com presto.erwin.com
```

Run Hadoop, Kafka and Presto docker containers
```
$ docker-compose up -d
```

Run the OpenPaymentStreamProcessor class
```
$ gradle build runMain
```

If you don't have an application that can post messages to Kafka, you can run the Kafka producer that comes with the Kafka install.
```
$ $KAFKA_HOME/bin/kafka-console-producer.sh --broker-list kafka.erwin.com:9092 --topic openpayments
>{"providerId":"132655","providerName":"GREGG D ALZATE","paymentAmount":90.87,"payerId":"100000000326","payerName":"DFINE, Inc"}
``` 

Download and run the [Presto CLI](https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.194/presto-cli-0.194-executable.jar)
```
$ mv presto-cli-0.194-executable.jar presto
$ chmod +x presto
$ ./presto --server presto.erwin.com:8080 --catalog hive --schema default
presto:default> select * from open_payment_record
``` 
   
To access Presto UI, click [here](http://presto.erwin.com:8080/)
To access Hadoop UI, click [here](http://hadoop.erwin.com:50070/dfshealth.html#tab-overview)
To access Zeppelin UI, click [here](http://zeppelin.erwin.com:8082/)