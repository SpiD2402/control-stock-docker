FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/categoria

COPY ./pom.xml /app

COPY ./Categoria/.mvn ./.mvn

COPY ./Categoria/mvnw .

COPY ./Categoria/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip  -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./Categoria/src ./src

RUN ./mvnw clean package -DskipTests


FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY  --from=builder /app/categoria/target/Categoria-0.0.1-SNAPSHOT.jar .


EXPOSE 8001

ENTRYPOINT ["java","-jar","Categoria-0.0.1-SNAPSHOT.jar"]
