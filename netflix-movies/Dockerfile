FROM openjdk:11
VOLUME /tmp
ADD ./target/netflix-movies-0.0.1-SNAPSHOT.jar netflix-movies.jar
ENTRYPOINT ["java","-jar","/netflix-movies.jar"]