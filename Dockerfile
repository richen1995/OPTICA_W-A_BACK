# Etapa 1: Construcción (Build)
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copiar archivos del proyecto
COPY . .

# Dar permisos de ejecución al wrapper de Maven y compilar
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar solo el archivo compilado desde la etapa anterior
# Nota: Como tu pom.xml dice <packaging>war</packaging>, buscamos el .war
COPY --from=build /app/target/*.war app.war

# Exponer el puerto que usa Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.war"]
