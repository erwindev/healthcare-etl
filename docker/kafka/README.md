# Kafka Docker Setup

Install Docker
* [MacOSX](https://docs.docker.com/docker-for-mac/install/)
* [Windows](https://docs.docker.com/docker-for-windows/install/)

Update hosts file
```
ip_of_kafka_vm   kafka.erwin.com
```

Build the image
```
$ docker-compose build
```

Run Kafka
```
$ docker-compose up
```

Create the topic (You will need Kafka installed locally in your environment)
```
$ kafka-topics.sh --create --zookeeper kafka.erwin.com:2181 --replication-factor 1 --partitions 1 --topic openpayments
```

Create the producer
```
$ kafka-console-producer.sh --broker-list kafka.erwin.com:9092 --topic openpayments
```
