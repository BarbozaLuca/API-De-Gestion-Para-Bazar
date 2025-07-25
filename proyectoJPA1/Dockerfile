FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/proyectoJPA1-0.0.1.jar
COPY ${JAR_FILE} app_proyectoJPA1.jar
ENTRYPOINT ["java", "-jar", "app_proyectoJPA1.jar"]