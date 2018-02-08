# Hadoop Docker Setup

Create the Virtual Machine
```
$ docker-machine create hadoop
$ eval $(docker-machine env hadoop)
```

Update hosts file
```
ip_of_hadoop_vm   hadoop.erwin.com
```

Build the image
```
$ docker-compose build
```

Run Hadoop
```
$ docker-compose up
```