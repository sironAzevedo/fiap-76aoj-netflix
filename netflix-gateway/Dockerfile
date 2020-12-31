FROM openjdk:11
VOLUME /tmp
EXPOSE 8765
ADD ./target/netflix-gateway-0.0.1-SNAPSHOT.jar netflix-gateway.jar
ENTRYPOINT ["java","-jar","/netflix-gateway.jar"]