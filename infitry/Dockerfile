FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD ./target/infitry-0.0.1.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]