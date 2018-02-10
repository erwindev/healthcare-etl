# Presto Docker Setup

Create the Virtual Machine
```
$ docker-machine create presto
$ eval $(docker-machine env presto)
```

Update hosts file
```
ip_of_presto_vm   presto.erwin.com
```

Build the image
```
$ docker-compose build
```

Run Presto
```
$ docker-compose up
```

Download and run the [Presto CLI](https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.194/presto-cli-0.194-executable.jar)
```
$ mv presto-cli-0.194-executable.jar presto
$ chmod +x presto
$ ./presto --server presto.erwin.com:8080 --catalog hive --schema default
```