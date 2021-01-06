#!/bin/bash

WORKDIR=$(pwd)

echo $WORKDIR

echo "############## Building Configuration Server ##############"
cd $WORKDIR/netflix-config-server
mvn clean package

echo "############## Building Eureka Server ##############"
cd $WORKDIR/netflix-eureka-server
mvn clean package

echo "############## Building Netflix User ##############"
cd $WORKDIR/netflix-user
mvn clean package -DskipTests=true

echo "############## Building Netflix Authorization ##############"
cd $WORKDIR/netflix-authorization
mvn clean package -DskipTests=true

echo "############## Building Netflix Category ##############"
cd $WORKDIR/netflix-category
mvn clean package -DskipTests=true

echo "############## Building Netflix Likes ##############"
cd $WORKDIR/netflix-likes
mvn clean package -DskipTests=true

echo "############## Building Netflix Movies ##############"
cd $WORKDIR/netflix-movies
mvn clean package -DskipTests=true

echo "############## Building Netflix Series ##############"
cd $WORKDIR/netflix-series
mvn clean package -DskipTests=true

echo "############## Building Netflix Help Desk ##############"
cd $WORKDIR/netflix-help-desk
mvn clean package -DskipTests=true

echo "############## Building Netflix Gateway ##############"
cd $WORKDIR/netflix-gateway
mvn clean package -DskipTests=true