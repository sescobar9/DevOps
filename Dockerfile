FROM openjdk:17-jdk
##ARG JAR_FILE=target/*.jar
ENV APP_WORKING_DIR=C:\User\santi\Downloads\DevOps\DevOps
WORKDIR ${APP_WORKING_DIR}
COPY ./target/DevOps-0.0.1-SNAPSHOT.jar ./src/app.jar
CMD ["java","-jar","./src/app.jar"]
EXPOSE 8080
