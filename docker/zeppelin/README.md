# Zeppelin

Install Docker
* [MacOSX](https://docs.docker.com/docker-for-mac/install/)
* [Windows](https://docs.docker.com/docker-for-windows/install/)

Update hosts file
```
ip_of_zeppelin_vm   zeppelin.erwin.com
```

Build the image
```
$ docker-compose build
```

Run Zeppelin
```
$ docker-compose up
```

To access Zeppelin UI, click [here](http://zeppelin.erwin.com:8082/)

Setup Presto Interpreter
* default.driver = com.facebook.presto.jdbc.PrestoDriver
* default.url = jdbc:presto://presto.erwin.com:8081/hive/default
* default.user = presto