FROM openjdk:11
VOLUME /tmp
ADD ./target/netflix-category-0.0.1-SNAPSHOT.jar netflix-category.jar
ENTRYPOINT ["java","-jar","/netflix-category.jar"]