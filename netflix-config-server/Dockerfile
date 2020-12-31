FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/netflix-config-server-0.0.1-SNAPSHOT.jar netflix-config-server.jar
ENTRYPOINT ["java","-jar","/netflix-config-server.jar"]