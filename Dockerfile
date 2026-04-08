# ETAPA 1: Compilación (Build)
# Usamos Corretto con Maven instalado para construir el proyecto
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /app

# Copiamos el archivo de configuración de dependencias
COPY pom.xml .
# Descargamos las dependencias (esto ayuda a que el próximo build sea más rápido)
RUN mvn dependency:go-offline

# Copiamos el código fuente y creamos el archivo JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Runtime)
# Usamos una imagen muy ligera de Amazon Corretto para correr la app
FROM amazoncorretto:17-al2023-headless
WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de Spring Boot
EXPOSE 8080

# Arrancamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
