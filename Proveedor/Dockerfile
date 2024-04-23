FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/proveedor

COPY ./pom.xml /app

COPY ./Proveedor/.mvn ./.mvn

COPY ./Proveedor/mvnw .

COPY ./Proveedor/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip  -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./Proveedor/src ./src

RUN ./mvnw clean package -DskipTests


FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY  --from=builder /app/proveedor/target/Proveedor-0.0.1-SNAPSHOT.jar .


EXPOSE 8002

ENTRYPOINT ["java","-jar","Proveedor-0.0.1-SNAPSHOT.jar"]
