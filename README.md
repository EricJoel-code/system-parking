# 🚗 Sistema de Gestión de Parqueadero

Aplicación web desarrollada con **Java y Spring Boot** para la gestión integral de un parqueadero. El sistema automatiza el control de entrada y salida de vehículos, la asignación de espacios, el cálculo de tarifas y la generación de información útil para la administración.

---

## 📌 Descripción

El **Sistema de Parqueadero** está diseñado para simplificar y optimizar la administración de estacionamientos. Permite registrar vehículos, controlar su permanencia, calcular el costo del servicio según el tiempo de uso y reducir errores humanos en la gestión diaria.

El proyecto sigue una **arquitectura en capas**, separando responsabilidades entre controladores, servicios, repositorios y modelos, asegurando escalabilidad y mantenimiento del sistema.

---

## 🎯 Objetivos del Sistema

* Automatizar el proceso de entrada y salida de vehículos
* Gestionar espacios disponibles en el parqueadero
* Calcular tarifas de forma automática
* Reducir errores humanos en la administración
* Mejorar la eficiencia operativa del parqueadero

---

## ⚙️ Funcionalidades Principales

* Registro de entrada de vehículos
* Registro de salida de vehículos
* Cálculo automático del tiempo de estacionamiento
* Cálculo de tarifas según el tiempo de uso
* Listado de vehículos estacionados
* Generación de información básica para control administrativo

---

## 🛠️ Tecnologías Utilizadas

### Backend

* **Java**
* **Spring Boot**
* **Spring Data JPA**

### Base de Datos

* **MySQL**

### Frontend

* **HTML5**
* **CSS3**
* **Thymeleaf**

### Herramientas

* **Maven**
* **Git & GitHub**

---

## 🧱 Arquitectura del Proyecto

El sistema utiliza una **arquitectura en capas**:

* **Controller**: Manejo de peticiones HTTP
* **Service**: Lógica de negocio
* **Repository**: Acceso a datos
* **Model**: Entidades del sistema

---

## 📁 Estructura del Proyecto

```bash
src/
 ├── .mvn/                       # Configuración de Maven
 ├── main/
 │    ├── java/
 │    │    ├── controllers/      # Controladores del sistema
 │    │    ├── models/           # Entidades del sistema
 │    │    ├── repositories/     # Acceso a base de datos (JPA)
 │    │    ├── services/         # Lógica de negocio
 │    │    └── SystemParkingApplication.java  # Clase principal
 │    └── resources/
 │         ├── static/           # Recursos estáticos (CSS, JS)
 │         └── templates/        # Vistas Thymeleaf
 ├── test/
 │    └── SystemParkingApplicationTests.java # Pruebas
 ├── .gitignore
 ├── mvnw
 ├── mvnw.cmd
 └── pom.xml                     # Dependencias del proyecto
```

---

## 🚀 Instalación y Ejecución

### Requisitos

* Java 17 o superior
* MySQL
* Maven

### Pasos

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/system-parking.git

# Configurar la base de datos en application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/parking_db
spring.datasource.username=usuario
spring.datasource.password=contraseña

# Ejecutar el proyecto
./mvnw spring-boot:run
```

La aplicación estará disponible en:

```
http://localhost:8080
```

---

## 📄 Estado del Proyecto

✅ Funcional

🔧 Posibles mejoras futuras:

* Autenticación y roles de usuario
* Reportes avanzados
* Integración con pagos
* Panel administrativo

---

## 👨‍💻 Autor

**Eric Cacuango**
Desarrollador de Software Junior

---

## 📝 Licencia

Proyecto desarrollado con fines educativos y de práctica profesional.
