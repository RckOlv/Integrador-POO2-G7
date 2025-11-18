# Retrospectiva - Iteración 1 (MVP)

**Fecha:** 18/11/2025

**Participantes:** | 
|--------------------|
| **Bauer Luciano** | 
| **Gimenez Rafael** | 
| **Lopez Luciano** | 
| **Navarro Eduardo**| 
| **Olivieri Ricardo**| 
| **Wilches Juan** |

## 1. ¿Qué salió bien? (Logros)
* **Objetivo Cumplido:** Se logró implementar el 100% del alcance del MVP (HUs 01 a 07), incluyendo la lógica compleja de Facturación Masiva.
* **Arquitectura Sólida:** La separación en capas (Controller, Service, Repository, Entity) demostró ser efectiva y facilitó la detección de errores.
* **Persistencia:** Se configuró exitosamente JPA/Hibernate, logrando que las tablas se generen automáticamente tanto en H2 (memoria) como para PostgreSQL.
* **Resolución de Bloqueos:** El equipo pudo resolver problemas técnicos críticos como la recursión infinita en JSON y errores de configuración de Maven/Spring Boot.

## 2. ¿Qué salió mal? (Desafíos y Problemas)
* **Configuración de Entorno:** Tuvimos problemas iniciales con versiones inexistentes en el `pom.xml` (Spring Boot 3.5.7) que impedían levantar el proyecto.
* **Mapeo de Objetos (DTOs):** Hubo errores de ejecución ("id must not be null") debido a la falta de coincidencia entre los nombres de variables en los DTOs (Java) y las claves del JSON enviado desde el cliente.
* **Serialización JSON:** Enfrentamos un error de "StackOverflow" por ciclos infinitos en las relaciones bidireccionales (`Cliente` <-> `Cuenta`), que requirió aplicar `@JsonIgnore`.

## 3. Plan de Mejora para la Iteración 2
* **Estandarización de DTOs:** Antes de codificar, definiremos estrictamente los nombres de los atributos en los DTOs para que coincidan con el contrato de la API (JSON).
* **Manejo de Relaciones:** Aplicar `@JsonIgnore` o DTOs de respuesta inmediatamente al crear relaciones bidireccionales para evitar problemas de serialización futuros.
* **Testing:** Incorporar pruebas unitarias básicas en la capa de servicio para validar la lógica sin depender de levantar toda la aplicación.
* **Incorporación de Frontend:** Se iniciará el desarrollo de la interfaz de usuario utilizando el framework **Angular** para consumir la API REST, consolidando una arquitectura "Full Stack" separada.