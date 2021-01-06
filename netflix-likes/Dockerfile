FROM openjdk:11
VOLUME /tmp
ADD ./target/netflix-likes-0.0.1-SNAPSHOT.jar netflix-likes.jar
ENTRYPOINT ["java","-jar","/netflix-likes.jar"]