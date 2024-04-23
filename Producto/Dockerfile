FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/producto

COPY ./pom.xml /app

COPY ./Producto/.mvn ./.mvn

COPY ./Producto/mvnw .

COPY ./Producto/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip  -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./Producto/src ./src

RUN ./mvnw clean package -DskipTests


FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY  --from=builder /app/producto/target/Producto-0.0.1-SNAPSHOT.jar .


EXPOSE 8001

ENTRYPOINT ["java","-jar","Producto-0.0.1-SNAPSHOT.jar"]
