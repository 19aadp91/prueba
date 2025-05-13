# 📚 Prueba API Libros & Autores 📖

Proyecto Java Spring Boot con Oracle Database, que gestiona autores y libros usando procedimientos almacenados.  
Incluye script SQL para crear las tablas, índices y procedimientos.

---

## 📦 Tecnologías usadas

- Java 23  
- Spring Boot 3.x  
- Oracle XE (o cualquier base de datos Oracle compatible)
- JDBC (Oracle Thin Driver)
- Docker

## 📑 Configuración previa

### Crear la base de datos y usuario en Oracle

- Crear un usuario en Oracle llamado `LIBROS_ADMIN`  
- Asignar permisos:

CREATE USER LIBROS_ADMIN IDENTIFIED BY 12345;
GRANT CONNECT, RESOURCE, DBA TO LIBROS_ADMIN;

## Conectarse con ese usuario y ejecutar el script:

@com/example/prueba/dataBase/database.sql

Este script crea:

Tablas AUTORES y LIBROS

Procedimientos almacenados para el CRUD completo

## ⚙️ Configuración application.properties

Ubicado en: src/main/resources/application.properties

spring.application.name=prueba
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=LIBROS_ADMIN
spring.datasource.password=12345
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

Cambia los valores de conexión según tu entorno local o servidor.

## ▶️ Ejecutar la aplicación localmente

./mvnw spring-boot:run

O desde tu IDE (VSCode o IntelliJ) ejecutando PruebaApplication.java

## 🐳 Ejecutar en Docker

### 📦 Construir imagen Docker

./mvnw clean package

Luego, construye la imagen:

docker build -t prueba-app .

### 🚀 Levantar contenedor

docker run -d -p 8080:8080 --name prueba-container prueba-app

La API quedará disponible en:
👉 http://localhost:8080

## 📝 Notas
Asegúrate de que Oracle Database esté corriendo y accesible desde el contenedor o la máquina local.

El script database.sql debe ejecutarse solo una vez para inicializar la base de datos.

Puedes cambiar credenciales y configuración en application.properties y rebuildar la imagen.