FROM openjdk:8
ADD target/heroku.jar heroku.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "heroku.jar"]