FROM openjdk:11
VOLUME /tmp
ADD ./target/netflix-help-desk-0.0.1-SNAPSHOT.jar netflix-help-desk.jar
ENTRYPOINT ["java","-jar","/netflix-help-desk.jar"]