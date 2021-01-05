FROM openjdk:11
VOLUME /tmp
ADD ./target/netflix-series-0.0.1-SNAPSHOT.jar netflix-series.jar
ENTRYPOINT ["java","-jar","/netflix-series.jar"]