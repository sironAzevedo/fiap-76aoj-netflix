FROM openjdk:11
VOLUME /tmp
ADD ./target/netflix-authorization-0.0.1-SNAPSHOT.jar netflix-authorization.jar
ENTRYPOINT ["java","-jar","/netflix-authorization.jar"]