FROM springio/gs-spring-boot-docker:latest
COPY Bangkok /etc/localtime
ADD target/x-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.war" ]