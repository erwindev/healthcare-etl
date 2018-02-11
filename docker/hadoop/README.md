# Hadoop Docker Setup

Install Docker
* [MacOSX](https://docs.docker.com/docker-for-mac/install/)
* [Windows](https://docs.docker.com/docker-for-windows/install/)

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

To access Hadoop UI, click [here](http://hadoop.erwin.com:50070/dfshealth.html#tab-overview)
