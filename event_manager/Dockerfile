FROM openjdk:8-jdk-alpine
VOLUME /var/log
ADD target/event_manager.jar app.jar
ADD src/main/resources/application.yml application.yml
ENTRYPOINT [ "java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]
