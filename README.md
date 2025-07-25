# 🧾 API de Gestión para Bazar - Proyecto Integrador Final

**Descripción breve:**  
API REST desarrollada con Spring Boot para gestionar productos y ventas en un bazar. Esta API centralizada será consumida por apps web y móviles. El backend está dockerizado para facilitar su ejecución en distintos entornos.  
Proyecto final del curso "Desarrollo de APIs en Java con Spring Boot" de TodoCode Academy.

---

## 📖 Escenario del problema

La dueña de un bazar ha experimentado un fuerte crecimiento en sus ventas. Esto ha hecho muy difícil registrar manualmente las operaciones y controlar el stock.  
Solicita una **API REST** que sea utilizada por una aplicación web (que será desarrollada por un amigo) y una futura aplicación móvil. Ambas deben consumir el mismo backend centralizado.

---

## 🎯 Objetivos del sistema

- Gestionar productos del bazar (CRUD completo).
- Registrar y consultar ventas.
- Actualizar el stock automáticamente en función de las ventas.
- Servir como backend común para frontend web y móvil.
- Facilitar el despliegue mediante Docker.

---

## 🛠️ Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- MySQL (persistencia)  
- Docker  
- Maven  
- Lombok  
- Swagger (para documentación)  
- Postman (para testing)

---

## 🧱 Arquitectura

La API está estructurada de forma monolítica con capas bien definidas

---

## 🚀 Cómo ejecutar el proyecto con Docker

### Prerrequisitos

- Docker y Docker Compose instalados
- JDK 17

### Pasos

 1. Clonar el repositorio:

 git clone https://github.com/tu-usuario/tu-repositorio.git
 
 2. Construir los servicios:
    
     docker-compose build
    
 4. Levantar los contenedores:

    docker-compose up

---

## 📬 Contacto

- Luca Nicolas Barboza - barbozaluca04@gmail.com
- Enlace del proyecto: https://github.com/BarbozaLuca/API-De-Gestion-Para-Bazar.git
