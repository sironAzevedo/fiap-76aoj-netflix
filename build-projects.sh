#!/bin/bash

WORKDIR=$(pwd)

echo $WORKDIR

echo "############## Building Configuration Server ##############"
cd $WORKDIR/netflix-config-server
mvn clean package