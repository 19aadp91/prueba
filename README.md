📚 Prueba API Libros & Autores 📖
Proyecto Java Spring Boot con Oracle Database, diseñado para gestionar autores y libros mediante procedimientos almacenados. Incluye un script SQL para la creación de tablas, índices y procedimientos.

🚀 Tecnologías utilizadas
Java 21

Spring Boot 3.x

Oracle XE (o cualquier base de datos Oracle compatible)

JDBC (Oracle Thin Driver)

Docker

🔧 Configuración previa
1️⃣ Crear la base de datos y usuario en Oracle
Ejecuta los siguientes comandos en Oracle SQL:

sql
CREATE USER LIBROS_ADMIN IDENTIFIED BY 12345;
GRANT CONNECT, RESOURCE, DBA TO LIBROS_ADMIN;
2️⃣ Ejecutar script SQL
Conéctate como LIBROS_ADMIN y ejecuta el siguiente script para crear las tablas y procedimientos:

sql
@com/example/prueba/dataBase/database.sql
Este script crea: ✔️ Tabla AUTORES ✔️ Tabla LIBROS ✔️ Procedimientos almacenados para el CRUD completo

⚙️ Configuración de la aplicación
Modifica el archivo application.properties ubicado en: 📂 src/main/resources/application.properties

properties
spring.application.name=prueba
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=LIBROS_ADMIN
spring.datasource.password=12345
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
🔹 Cambia los valores de conexión según tu entorno local o servidor.

▶️ Ejecutar la aplicación localmente
Desde la terminal, ejecuta:

sh
./mvnw spring-boot:run
También puedes ejecutarlo desde tu IDE (VSCode o IntelliJ) corriendo PruebaApplication.java.

🐳 Ejecutar en Docker
1️⃣ Construir la imagen Docker
sh
./mvnw clean package
docker build -t prueba-app .
2️⃣ Levantar el contenedor
sh
docker run -d -p 8080:8080 --name prueba-container prueba-app
🔹 La API estará disponible en: 👉 http://localhost:8080

📝 Notas importantes
Oracle Database debe estar corriendo y accesible desde el contenedor o la máquina local.

El script database.sql solo necesita ejecutarse una vez para inicializar la base de datos.

Puedes cambiar credenciales y configuración en application.properties y volver a construir la imagen.