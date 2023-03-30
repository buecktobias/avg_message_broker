FROM openjdk:18-jdk-slim
RUN mkdir /app
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean \
RUN mvn install
RUN mvn package

CMD ["java","-jar", "/app/target/activemq-1.jar", "startDockerContainer"]
