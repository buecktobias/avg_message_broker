FROM openjdk:18-jdk-slim

COPY . .

RUN javac Main.java

CMD sh -c 'sleep 30 && java Main startDockerContainer'
