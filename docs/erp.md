# Especificación de Requisitos del Software (ERP)
# Sistema de Facturación de Servicios

## 1. Introducción

El presente documento detalla los requisitos funcionales (RF) y no funcionales (RNF) para el Sistema de Facturación de Servicios. El objetivo es construir un sistema backend (API REST) en Java/Spring Boot que gestione clientes, cuentas, servicios, y permita la facturación masiva, individual, anulación y registro de pagos, cumpliendo con las normativas fiscales de IVA de Argentina.

## 2. Requisitos Funcionales (RF)

Los requisitos funcionales se agrupan por la iteración en que serán implementados.

### Iteración 1: Núcleo de Facturación (MVP)

* **RF-01: Gestión de Clientes:** El sistema debe permitir el ABM (Alta, Baja lógica, Modificación) de `Clientes` (Razón Social, CUIT, Domicilio, `CondicionIVA`). Debe validar la unicidad del CUIT.
* **RF-02: Gestión de Cuentas:** El sistema debe gestionar una `Cuenta` por `Cliente` (relación 1 a 1), manejando un `EstadoCuenta` (ACTIVA, SUSPENDIDA, BAJA).
* **RF-03: Gestión de Catálogo de Servicios:** El sistema debe permitir el ABM de `Servicios` (Nombre, Monto Base, Alícuota IVA).
* **RF-04: Asignación de Servicios:** El sistema debe permitir asociar y desasociar `Servicios` (RF-03) a `Cuentas` (RF-02) a través de la entidad `ServicioContratado`.
* **RF-05: Proceso de Facturación Masiva:** El sistema debe proveer una función para generar `Facturas` a todas las `Cuentas` en estado `ACTIVA`, basándose en sus `ServicioContratado`.
* **RF-06: Generación de Factura Individual:** El sistema debe permitir la creación de una `Factura` para una `Cuenta` específica, con ítems manuales.
* **RF-07: Anulación de Facturas:** El sistema debe permitir anular una `Factura` (cambiando su estado a `ANULADA`) y generar la `NotaDeCredito` correspondiente (relación 1 a 1).

### Iteración 2: Pagos, Reportes y Seguridad

* **RF-08: Registro de Pagos:** El sistema debe permitir registrar `Pagos` (totales o parciales) y asociarlos a una `Cuenta`, actualizando su `saldo`.
* **RF-09: Reporte de Cuenta Corriente:** El sistema debe ser capaz de listar el historial de movimientos (Facturas, Notas de Crédito, Pagos) y el saldo actual de una `Cuenta`.
* **RF-10: Reporte de Facturas Pendientes:** El sistema debe proveer un listado de todas las `Facturas` cuyo estado sea `PENDIENTE`.
* **RF-11: Autenticación de Usuarios:** El sistema debe implementar un mecanismo de Login (usuario/contraseña) para validar el acceso (ej. Spring Security).
* **RF-12: Autorización por Roles:** El sistema debe definir Roles (ej. `ROLE_ADMIN`, `ROLE_OPERADOR`) y restringir el acceso a funcionalidades sensibles (ej. RF-05, RF-07) solo a roles autorizados.
* **RF-13: Gestión de Usuarios:** El sistema debe permitir el ABM de `Usuarios` del sistema (usuario, contraseña, rol).
* **RF-14: Auditoría de Operaciones:** El sistema debe registrar un log de auditoría para acciones críticas (ej. RF-07, modificación de RF-03), almacenando qué usuario la realizó y cuándo.

## 3. Requisitos No Funcionales (RNF)

* **RNF-01: Arquitectura de Software:** El sistema debe implementarse en Java (JDK 17+) utilizando el framework Spring Boot (v3+). Debe seguir la arquitectura de capas (Controller, Service, Repository, Entity).
* **RNF-02: Persistencia:** La base de datos principal debe ser PostgreSQL. La interacción se realizará a través de Spring Data JPA.
* **RNF-03: Precisión Financiera:** Todos los atributos monetarios (precios, totales, saldos) deben implementarse obligatoriamente con el tipo de dato `Decimal` de Java.
* **RNF-04: API REST:** La comunicación con el sistema debe ser a través de una API RESTful, utilizando JSON como formato de intercambio de datos.
* **RNF-05: Seguridad:** El acceso a la API debe estar protegido (RNF-11). Las contraseñas de los usuarios (RNF-13) deben almacenarse hasheadas (ej. BCrypt).
* **RNF-06: Mantenibilidad:** El código debe seguir las convenciones de nomenclatura de Java y estar documentado (JavaDoc en servicios clave).