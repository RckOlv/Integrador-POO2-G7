# Historias de Usuario

---

##  Iteración 1 — Fundamentos del Sistema (Gestión y Facturación Básica)

---

### **HU-01: Gestión de Clientes**
> **Como** operador del sistema,  
> **quiero** poder registrar, editar y eliminar clientes,  
> **para** mantener actualizada la información de las cuentas y su condición fiscal (IVA).

**Criterios de aceptación:**
- El sistema permite registrar nuevos clientes con datos básicos (nombre, CUIT, dirección, email, teléfono).  
- El sistema almacena la condición fiscal (IVA Responsable Inscripto, Monotributista, Exento, etc.).  
- Se pueden editar o eliminar clientes existentes.  
- Se valida que no haya CUIT duplicados.

---

### **HU-02: Gestión de Cuentas**
> **Como** operador,  
> **quiero** poder crear, editar y eliminar cuentas asociadas a cada cliente,  
> **para** administrar correctamente los servicios contratados por cada uno.

**Criterios de aceptación:**
- Cada cuenta está vinculada a un cliente.  
- Se pueden editar los datos de una cuenta y actualizar su estado (activa/inactiva).  
- No se permiten cuentas duplicadas para un mismo cliente.  

---

### **HU-03: Registro de Condición Fiscal**
> **Como** operador,  
> **quiero** registrar la condición fiscal de cada cliente,  
> **para** aplicar correctamente los impuestos en la facturación.

**Criterios de aceptación:**
- Se pueden seleccionar las condiciones fiscales predefinidas (RI, Monotributista, Exento, CF).  
- El tipo de IVA se aplica automáticamente según la condición fiscal.  
- El sistema permite modificar la condición fiscal cuando sea necesario.  

---

### **HU-04: Inicio de Sesión**
> **Como** usuario,  
> **quiero** iniciar sesión en el sistema,  
> **para** acceder según mi rol (operador, supervisor o administrador).

**Criterios de aceptación:**
- Se valida usuario y contraseña.  
- Solo los usuarios autenticados pueden ingresar al sistema.  
- Se muestra la interfaz y funcionalidades de acuerdo al rol.  

---

### **HU-05: Generación de Facturas Individuales**
> **Como** operador del sistema,  
> **quiero** poder generar una factura individual para un cliente,  
> **para** registrar el cobro de un servicio prestado.

**Criterios de aceptación:**
- La factura incluye número, fecha, cliente, detalle del servicio y monto total con IVA.  
- Se valida la condición fiscal del cliente para calcular el IVA correspondiente.  
- La factura se almacena en la base de datos y puede visualizarse en un listado.  

---

### **HU-06: Visualización de Clientes y Facturas**
> **Como** operador,  
> **quiero** visualizar una lista de todos los clientes y sus facturas,  
> **para** consultar fácilmente el estado de las cuentas.

**Criterios de aceptación:**
- El sistema muestra una tabla con los clientes y sus facturas asociadas.  
- Permite filtrar por nombre, CUIT o fecha de facturación.  
- Se pueden exportar los listados a PDF o Excel.  

---

### **HU-07: Anulación de Facturas**
> **Como** administrador,  
> **quiero** poder anular una factura emitida,  
> **para** corregir errores o cancelaciones.

**Criterios de aceptación:**
- La factura anulada cambia su estado a “Anulada”.  
- No puede editarse ni contabilizarse una factura anulada.  
- Se mantiene un registro del motivo de anulación y usuario responsable.  

---

## Iteración 2 — Automatización, Pagos, Reportes y Control

---

### **HU-08: Facturación Masiva por Período**
> **Como** administrador del sistema,  
> **quiero** generar facturas en forma masiva para todos los clientes activos en un período,  
> **para** agilizar el proceso mensual de facturación.

**Criterios de aceptación:**
- Se selecciona un período (mes/año).  
- El sistema genera automáticamente las facturas correspondientes a cada cliente.  
- Se notifican los resultados del proceso (éxitos y errores).  

---

### **HU-09: Registro de Pagos**
> **Como** operador,  
> **quiero** registrar los pagos recibidos de los clientes,  
> **para** mantener actualizado el estado de las facturas.

**Criterios de aceptación:**
- Se puede registrar un pago indicando fecha, monto y medio de pago.  
- El sistema actualiza el estado de la factura a “Pagada”.  
- Se pueden consultar los pagos realizados por cliente.  

---

### **HU-10: Reportes de Facturación**
> **Como** supervisor,  
> **quiero** generar reportes de facturación por período,  
> **para** analizar el rendimiento y control financiero.

**Criterios de aceptación:**
- El sistema permite generar reportes filtrados por fechas, cliente o estado.  
- Los reportes pueden exportarse en formato PDF o Excel.  
- Los totales se calculan automáticamente.  

---

### **HU-11: Control de Facturas Pendientes**
> **Como** operador,  
> **quiero** visualizar las facturas pendientes de pago,  
> **para** realizar seguimiento y cobranza de los clientes morosos.

**Criterios de aceptación:**
- Se muestran facturas impagas con nombre del cliente, importe y días de demora.  
- Se pueden aplicar filtros por antigüedad o monto.  
- Se genera alerta si una factura supera los 30 días sin pago.  

---

### **HU-12: Auditoría del Sistema**
> **Como** administrador,  
> **quiero** disponer de un registro de auditoría,  
> **para** conocer qué usuarios realizaron cada acción en el sistema.

**Criterios de aceptación:**
- Se registra cada acción importante (alta, baja, modificación, login).  
- El registro incluye usuario, fecha, hora y descripción de la acción.  
- Los logs se pueden consultar y filtrar por usuario o fecha.  

---

### **HU-13: Configuración del IVA**
> **Como** administrador,  
> **quiero** configurar el valor del IVA aplicable,  
> **para** que el sistema calcule correctamente los montos de facturación.

**Criterios de aceptación:**
- El valor de IVA se puede modificar desde el panel de configuración.  
- El nuevo valor se aplica automáticamente a las facturas futuras.  
- Se guarda un historial de cambios de configuración.  

---

### **HU-14: Gestión de Roles y Permisos**
> **Como** administrador,  
> **quiero** gestionar los roles y permisos de los usuarios,  
> **para** controlar el acceso a las distintas funcionalidades del sistema.

**Criterios de aceptación:**
- Se pueden crear, editar y eliminar roles.  
- Cada rol tiene permisos específicos para acceder a ciertos módulos.  
- El sistema valida los permisos antes de permitir una acción.  
