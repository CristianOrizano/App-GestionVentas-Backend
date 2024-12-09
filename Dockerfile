# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

  # Exponer el puerto en el que la aplicación escuchará
EXPOSE 8091

  # Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

  # Copiar el archivo JAR generado dentro del contenedor
COPY target/gestionventas-0.0.1-SNAPSHOT.jar app.jar

  # Ejecutar la aplicación cuando el contenedor inicie
CMD ["java", "-jar", "app.jar"]