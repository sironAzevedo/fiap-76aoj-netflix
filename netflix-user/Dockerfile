FROM openjdk:11
VOLUME /tmp
ADD ./target/netflix-user-0.0.1-SNAPSHOT.jar netflix-user.jar
ENTRYPOINT ["java","-jar","/netflix-user.jar"]