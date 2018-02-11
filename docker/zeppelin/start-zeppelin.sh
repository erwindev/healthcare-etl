#!/usr/bin/env bash

$ZEPPELIN_HOME/bin/zeppelin-daemon.sh start

# keep container running
tail -f /dev/null