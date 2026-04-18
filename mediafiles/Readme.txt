//CONEXION BASE DE DATOS DE FORMA LOCAL SIN HEROKU
spring.application.name=luxtraking
spring.datasource.url=jdbc:postgresql://localhost:5432/luxtraking
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql:true

spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=10MB
media.location=mediafiles