# Usa una imagen base de Amazon Corretto para Java 17
FROM amazoncorretto:17 as builder

# Copia los archivos de configuración y el código fuente del proyecto
COPY build.gradle .
COPY src ./src

# Copia el Gradle Wrapper
COPY gradlew .
COPY gradle ./gradle
COPY settings.gradle .

# Ejecuta el comando para construir el proyecto y generar el archivo JAR
RUN chmod +x ./gradlew
RUN ./gradlew build -x test --info

# Usa una imagen más liviana de Amazon Corretto para ejecutar la aplicación
FROM amazoncorretto:17-alpine

# Copia el archivo JAR generado desde la etapa anterior
COPY --from=builder build/libs/catalogproducts-0.0.1-SNAPSHOT.jar /app/catalogproducts.jar

# Establece el directorio de trabajo en /app
WORKDIR /app

# Expone el puerto 3030
EXPOSE 3030

# Define las variables de entorno para la configuración
ENV CLOUDKARAFKA_BROKERS ${CLOUDKARAFKA_BROKERS}
ENV CLOUDKARAFKA_PASSWORD ${CLOUDKARAFKA_PASSWORD}
ENV CLOUDKARAFKA_TOPIC_PREFIX ${CLOUDKARAFKA_TOPIC_PREFIX}
ENV CLOUDKARAFKA_USERNAME ${CLOUDKARAFKA_USERNAME}
ENV MONGODB_DATABASE ${MONGODB_DATABASE}
ENV MONGODB_PASSWORD ${MONGODB_PASSWORD}
ENV MONGODB_USERNAME ${MONGODB_USERNAME}

# Ejecuta el comando para iniciar la aplicación
CMD ["java", "-jar", "/app/catalogproducts.jar"]