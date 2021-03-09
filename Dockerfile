FROM openjdk:8
COPY ./target/DevOpsCalc-1.0-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java", "-cp", "DevOpsCalc-1.0-SNAPSHOT.jar", "Calculator"]