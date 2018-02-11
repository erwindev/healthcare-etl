# Realtime ETL Data Pipeline
This is a sample project that demonstrates a realtime data pipeline using Hadoop, Kafka and Presto.  For this project we wil be using the Open Payments Data from Centers fpr Medicare and Medicaid Services.

## Setup the Healthcare ETL Application
**Install Docker**
* [MacOSX](https://docs.docker.com/docker-for-mac/install/)
* [Windows](https://docs.docker.com/docker-for-windows/install/)

**Clone the project**
```
$ git clone https://github.com/erwindev/healthcare-etl.git
$ cd docker
``` 

**Build Hadoop, Kafka, Presto, Zeppelin**
```
$ cd docker
$ docker-compose build hadoop
$ docker-compose build kafka
$ docker-compose build presto
$ docker-compose build zeppelin
```

**Update hosts file**
```
localhost   hadoop.erwin.com kafka.erwin.com presto.erwin.com zeppelin.erwin.com
```

## Run Hadoop, Kafka, Presto and Zeppelin
```
$ docker-compose up -d
```
To access Hadoop UI, click [here](http://hadoop.erwin.com:50070/dfshealth.html#tab-overview)
To access Presto UI, click [here](http://presto.erwin.com:8080/) 

**Download and run the [Presto CLI](https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.194/presto-cli-0.194-executable.jar)**
```
$ mv presto-cli-0.194-executable.jar presto
$ chmod +x presto
$ ./presto --server presto.erwin.com:8080 --catalog hive --schema default
```

**Configure Zeppelin**
* Go to Zeppelin UI by clicking [here](http://zeppelin.erwin.com:8082/)
* Add Presto Interpreter
* Use the following settings for the Presto Interpreter
   * default.driver = com.facebook.presto.jdbc.PrestoDriver
   * default.url = jdbc:presto://presto.erwin.com:8081/hive/default
   * default.user = presto
   * artifact = com.facebook.presto:presto-jdbc:jar:0.170

## Run the Healthcare ETL application

**Run Apache Spark Streaming application**
```
$ git clone https://github.com/erwindev/healthcare-etl
$ cd healthcare-etl
$ gradle build runMain
```

**Send Open Payments Data to Kafka**

If you don't have an application that can post messages to Kafka, you can run the Kafka producer that comes with the Kafka install.  You will need to install Kafka locally in your machine.
```
$ $KAFKA_HOME/bin/kafka-console-producer.sh --broker-list kafka.erwin.com:9092 --topic openpayments
>{"providerId":"132655","providerName":"GREGG D ALZATE","paymentAmount":90.87,"payerId":"100000000326","payerName":"DFINE, Inc"}
```

Another way to send Open Payments data to Kafka is to run the Open Payment Producer application.  There is sample data that comes with the repo.  If you need a larger dataset, you will need to download data from [CMS.gov](https://www.cms.gov/OpenPayments/Explore-the-Data/Dataset-Downloads.html). 
```
$ git clone https://github.com/erwindev/openpayment-producer.git
$ gradle build
$ java -jar build/libs/openpayment-producer-1.0.jar
``` 

## Read Open Payments Data from HDFS

Read the Open Payments Record Parquet file in HDFS
```
$ gradle build runParquetReader
```

Read the Open Payments Record through Presto
```
$ gradle build runPrestoDBReader
```

Create a notebook using Presto as the default interpreter.  Run the following SQL query in Presto.
```
%presto 

SELECT 
providername, 
sum(CAST(payeramount AS decimal(10,2))) totalpayment
FROM open_payment_record 
group BY providerid, providername
order by totalpayment desc
limit 10
```