#  Diseño y Planificación - Iteración 1

---

## 1. Trabajo en equipo

**(COMPLETAR POR EL EQUIPO: Describan qué trabajo ha realizado cada integrante).**

| Integrante         | Tareas Asignadas (Basadas en la sección 5. Tareas) |
|--------------------|----------------------------------------------------|
| **Bauer Luciano**  | HU-02: Gestión de Estados de Cuenta |
| **Gimenez Rafael** | HU-04: Asignación de Servicios a Cuentas |
| **Lopez Luciano**  | HU-06: Generación de Factura Individual |
| **Navarro Eduardo**| HU-05: Generación de Facturación Masiva<br>HU-01: Gestión de Clientes |
| **Olivieri Ricardo** | HU-03: Gestión de Catálogo de Servicios |
| **Wilches Juan**   | HU-07: Anulación de Facturas |

---

## 2. Diseño OO

![Diagrama de CLases](../Diagrama%20de%20clase%20-%20POO%202.drawio.png)

## 3. Wireframe y caso de uso

*(Espacio para wireframes y diagramas de casos de uso — pantallas clave del sistema.)*

---

## 4. Backlog de iteración

A continuación, se listan las **7 Historias de Usuario (HUs)** que componen el alcance del MVP y que serán implementadas en esta Iteración 1:

---

### **HU-01: Gestión de Clientes**
> **Como** operador del sistema,  
> **quiero** poder registrar y gestionar los datos fiscales de los clientes,  
> **para** mantener actualizada la base de datos de facturación.

**Criterios de aceptación:**
- Puedo crear un Cliente (Razón Social, CUIT, Domicilio, Cond. Fiscal).
- Puedo modificar los datos de un Cliente.
- El sistema valida que el CUIT no esté duplicado.
- Las condiciones fiscales son: Responsable Inscripto, Monotributista, Exento, Consumidor Final.

---

### **HU-02: Gestión de Estado de Cuentas**
> **Como** operador del sistema,  
> **quiero** poder gestionar la cuenta comercial de un cliente,  
> **para** habilitar o deshabilitar la facturación.

**Criterios de aceptación:**
- Al crear un Cliente (HU-01), se crea su Cuenta asociada en estado **"ACTIVA"**.
- Puedo cambiar el estado de una Cuenta (ACTIVA, SUSPENDIDA, BAJA).
- El sistema usa el estado **"ACTIVA"** como condición para la facturación masiva (HU-05).

---

### **HU-03: Gestión de Catálogo de Servicios**
> **Como** administrador,  
> **quiero** poder crear, editar y consultar los servicios que ofrece la empresa,  
> **para** definir qué se puede facturar y a qué precios.

**Criterios de aceptación:**
- Puedo crear un Servicio con (Nombre, Monto Base, Alícuota IVA).
- Puedo modificar el Monto Base o la Alícuota de un servicio existente.
- Puedo ver un listado de todos los servicios ofrecidos.

---

### **HU-04: Asignación de Servicios a Cuentas**
> **Como** operador,  
> **quiero** poder asignar uno o más servicios del catálogo a la cuenta de un cliente,  
> **para** definir qué se le debe facturar recurrentemente en el proceso masivo.

**Criterios de aceptación:**
- Puedo seleccionar una Cuenta y ver los servicios que tiene contratados.
- Puedo agregar un servicio del catálogo (HU-03) a esa cuenta.
- Puedo quitar un servicio contratado de esa cuenta.

---

### **HU-05: Generación de Facturación Masiva**
> **Como** administrador,  
> **quiero** poder iniciar el proceso de facturación masiva para un período (Mes/Año),  
> **para** generar automáticamente las facturas de todos los clientes activos.

**Criterios de aceptación:**
- Debo poder indicar el período a facturar (ej. `10/2025`).
- El sistema debe tomar todas las Cuentas en estado **"ACTIVA"** (HU-02).
- Para cada cuenta, debe generar una Factura con los ítems de sus "servicios contratados" (HU-04).
- El cálculo de IVA debe ser correcto.
- Se debe registrar el resultado del proceso (ej. “Se generaron 150 facturas exitosamente”).

---

### **HU-06: Generación de Factura Individual**
> **Como** operador,  
> **quiero** poder generar una factura individual para un cliente específico,  
> **para** registrar cobros por fuera del abono mensual.

**Criterios de aceptación:**
- Puedo seleccionar una Cuenta.
- Puedo agregar ítems manualmente (descripción, monto, IVA) a la factura.
- La factura se genera y almacena con un número único y estado **PENDIENTE**.

---

### **HU-07: Anulación de Facturas**
> **Como** administrador,  
> **quiero** poder anular una factura emitida (generando una Nota de Crédito),  
> **para** corregir errores de facturación.

**Criterios de aceptación:**
- Puedo seleccionar una factura y **"Anularla"**.
- Se debe pedir un motivo de anulación.
- El sistema genera una **NotaDeCredito** asociada por el mismo importe (relación 1 a 1).
- La factura original queda marcada como **ANULADA** y no puede anularse de nuevo.
- La **NotaDeCredito** impacta en el saldo de la cuenta corriente del cliente.

---

## 5. Tareas

**Desglose técnico de las tareas necesarias para implementar el backlog de la iteración 1.**

---

###  Tareas HU-01 (Clientes)
- `T-01.1 (Entidad)`: Cliente.java, CondicionIVA.java (enum)
- `T-01.2 (Repo)`: ClienteRepository (con findByCuit)
- `T-01.3 (Servicio)`: ClienteService (crear, listar, validar CUIT)
- `T-01.4 (Controlador)`: ClienteController (POST, GET)
- `T-01.5 (DTO)`: ClienteDTO, CrearClienteRequest

---

###  Tareas HU-02 (Cuentas)
- `T-02.1 (Entidad)`: Cuenta.java, EstadoCuenta.java (enum)
- `T-02.2 (Repo)`: CuentaRepository
- `T-02.3 (Servicio)`: Modificar ClienteService (para crear Cuenta)
- `T-02.4 (Servicio)`: CuentaService (método cambiarEstadoCuenta)
- `T-02.5 (Controlador)`: CuentaController (endpoint PUT /estado)

---

### Tareas HU-03 (Servicios)
- `T-03.1 (Entidad)`: Servicio.java
- `T-03.2 (Repo)`: ServicioRepository
- `T-03.3 (Servicio)`: ServicioService (CRUD básico)
- `T-03.4 (Controlador)`: ServicioController (POST, GET, PUT)

---

###  Tareas HU-04 (Asignar Servicios)
- `T-04.1 (Entidad)`: ServicioContratado.java (con @ManyToOne)
- `T-04.2 (Repo)`: ServicioContratadoRepository
- `T-04.3 (Servicio)`: CuentaService (métodos asignarServicio, quitarServicio)
- `T-04.4 (Controlador)`: CuentaController (endpoints POST /servicios, DELETE /servicios)

---

###  Tareas HU-05 (Facturación Masiva)
- `T-05.1 (Entidad)`: Factura.java, ItemFactura.java, EstadoFactura.java (enum)
- `T-05.2 (Repo)`: FacturaRepository, ItemFacturaRepository
- `T-05.3 (Servicio)`: FacturacionService (interfaz e impl.)
- `T-05.4 (Servicio)`: Lógica de ejecutarFacturacionMasiva(mes, anio)
- `T-05.5 (Controlador)`: FacturacionController (endpoint POST /masiva)

---

###  Tareas HU-06 (Factura Individual)
- `T-06.1 (DTO)`: FacturaIndividualRequest
- `T-06.2 (Servicio)`: FacturacionService (método crearFacturaIndividual)
- `T-06.3 (Controlador)`: FacturacionController (endpoint POST /individual)

---

###  Tareas HU-07 (Anulación)
- `T-07.1 (Entidad)`: NotaDeCredito.java (con @OneToOne a Factura)
- `T-07.2 (Repo)`: NotaDeCreditoRepository
- `T-07.3 (Servicio)`: FacturacionService (método anularFactura)
- `T-07.4 (Controlador)`: FacturacionController (endpoint POST /{idFactura}/anular)
