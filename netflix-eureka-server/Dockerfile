FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/netflix-eureka-server-0.0.1-SNAPSHOT.jar netflix-eureka-server.jar
ENTRYPOINT ["java","-jar","/netflix-eureka-server.jar"]