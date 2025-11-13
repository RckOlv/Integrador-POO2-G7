# Roadmap del Proyecto (Historias de Usuario)

Este documento presenta el plan tentativo de las Historias de Usuario (HUs) que se implementarán en cada iteración del proyecto.

---

## Iteración 1 — Fundamentos del Sistema (MVP) - (7 HUs)

### HU-01: Gestión de Clientes
> **Como** operador del sistema,
> **quiero** poder registrar y gestionar los datos fiscales de los clientes,
> **para** mantener actualizada la base de datos de facturación.

**Criterios de aceptación:**
* Puedo crear un Cliente (Razón Social, CUIT, Domicilio, Cond. Fiscal).
* Puedo modificar los datos de un Cliente.
* El sistema valida que el CUIT no esté duplicado.
* Las condiciones fiscales son: Responsable Inscripto, Monotributista, Exento, Consumidor Final.

### HU-02: Gestión de Estado de Cuentas
> **Como** operador del sistema,
> **quiero** poder gestionar la cuenta comercial de un cliente,
> **para** habilitar o deshabilitar la facturación.

**Criterios de aceptación:**
* Al crear un Cliente (HU-01), se crea su Cuenta asociada en estado "ACTIVA".
* Puedo cambiar el estado de una Cuenta (ACTIVA, SUSPENDIDA, BAJA).
* El sistema usa el estado "ACTIVA" como condición para la facturación masiva (HU-05).

### HU-03: Gestión de Catálogo de Servicios
> **Como** administrador,
> **quiero** poder crear, editar y consultar los servicios que ofrece la empresa,
> **para** definir qué se puede facturar y a qué precios.

**Criterios de aceptación:**
* Puedo crear un Servicio con (Nombre, Monto Base, Alícuota IVA).
* Puedo modificar el Monto Base o la Alícuota de un servicio existente.
* Puedo ver un listado de todos los servicios ofrecidos.

### HU-04: Asignación de Servicios a Cuentas
> **Como** operador,
> **quiero** poder asignar uno o más servicios del catálogo a la cuenta de un cliente,
> **para** definir qué se le debe facturar recurrentemente en el proceso masivo.

**Criterios de aceptación:**
* Puedo seleccionar una Cuenta y ver los servicios que tiene contratados.
* Puedo agregar un servicio del catálogo (HU-03) a esa cuenta.
* Puedo quitar un servicio contratado de esa cuenta.

### HU-05: Generación de Facturación Masiva
> **Como** administrador,
> **quiero** poder iniciar el proceso de facturación masiva para un período (Mes/Año),
> **para** generar automáticamente las facturas de todos los clientes activos.

**Criterios de aceptación:**
* Debo poder indicar el período a facturar (ej. 10/2025).
* El sistema debe tomar todas las Cuentas en estado "ACTIVA" (HU-02).
* Para cada cuenta, debe generar una Factura con los ítems de sus "servicios contratados" (HU-04).
* El cálculo de IVA debe ser correcto.
* Se debe registrar el resultado del proceso.

### HU-06: Generación de Factura Individual
> **Como** operador,
> **quiero** poder generar una factura individual para un cliente específico,
> **para** registrar cobros por fuera del abono mensual.

**Criterios de aceptación:**
* Puedo seleccionar una Cuenta.
* Puedo agregar ítems manualmente (descripción, monto, IVA) a la factura.
* La factura se genera y almacena con un número único y estado `PENDIENTE`.

### HU-07: Anulación de Facturas
> **Como** administrador,
> **quiero** poder anular una factura emitida (generando una Nota de Crédito),
> **para** corregir errores de facturación.

**Criterios de aceptación:**
* Puedo seleccionar una factura y "Anularla".
* Se debe pedir un motivo de anulación.
* El sistema genera una `NotaDeCredito` asociada por el mismo importe (relación 1 a 1).
* La factura original queda marcada como `ANULADA` y no puede anularse de nuevo.
* La `NotaDeCredito` impacta en el saldo de la cuenta corriente del cliente.

---

## Iteración 2 — Pagos, Reportes y Seguridad - (7 HUs)

### HU-08: Registro de Pagos
> **Como** operador,
> **quiero** registrar los pagos (totales o parciales) recibidos de los clientes,
> **para** mantener actualizado el saldo de su cuenta corriente.

**Criterios de aceptación:**
* Puedo seleccionar una Cuenta y registrar un pago (fecha, monto, medio de pago).
* El pago impacta en el `saldo` de la `Cuenta`.

### HU-09: Consulta de Cuenta Corriente (Reporte)
> **Como** operador,
> **quiero** consultar el historial de movimientos (facturas, notas de crédito, pagos) de una cuenta,
> **para** saber su saldo actual.

**Criterios de aceptación:**
* Al ver una `Cuenta`, puedo ver un listado cronológico de sus comprobantes (Facturas, NC) y Pagos.
* El sistema muestra un `saldo` total.

### HU-10: Listado de Facturas Pendientes
> **Como** operador,
> **quiero** visualizar un listado de todas las facturas pendientes de pago,
> **para** gestionar la cobranza.

**Criterios de aceptación:**
* El sistema muestra una lista de facturas cuyo estado es `PENDIENTE`.
* La lista incluye (Cliente, Nro Factura, Fecha, Monto Total, Saldo Pendiente).

### HU-11: Inicio de Sesión (Autenticación)
> **Como** usuario (Operador, Administrador),
> **quiero** iniciar sesión en el sistema con usuario y contraseña,
> **para** acceder al sistema de forma segura.

**Criterios de aceptación:**
* Se valida usuario y contraseña contra la base de datos (Spring Security).
* Si es incorrecto, muestra un error.

### HU-12: Control de Acceso por Roles (Autorización)
> **Como** administrador,
> **quiero** que el sistema restrinja funcionalidades según el rol del usuario,
> **para** asegurar que solo personal autorizado realice acciones sensibles.

**Criterios de aceptación:**
* Existen roles (ej. `ROLE_ADMIN`, `ROLE_OPERADOR`).
* Solo `ROLE_ADMIN` puede ejecutar la Facturación Masiva (HU-05) y Anular Facturas (HU-07).

### HU-13: Gestión de Usuarios (CRUD)
> **Como** administrador,
> **quiero** poder crear, editar y consultar las cuentas de usuario del sistema,
> **para** gestionar quiénes pueden acceder.

**Criterios de aceptación:**
* Puedo crear un nuevo usuario asignándole un rol.
* Puedo ver un listado de los usuarios del sistema.

### HU-14: Auditoría de Operaciones Sensibles
> **Como** administrador,
> **quiero** disponer de un registro de auditoría de acciones sensibles,
> **para** controlar quién hizo qué y cuándo.

**Criterios de aceptación:**
* Se registra cada anulación de factura (HU-07), indicando qué usuario la realizó.
* Se registra cada modificación de precios de servicios (HU-03).