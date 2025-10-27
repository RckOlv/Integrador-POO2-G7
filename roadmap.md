# Historias de Usuario

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

### **HU-02: Generación de Facturas Individuales**
> **Como** operador del sistema,  
> **quiero** poder generar una factura individual para un cliente,  
> **para** registrar el cobro de un servicio prestado.

**Criterios de aceptación:**
- La factura incluye número, fecha, cliente, detalle del servicio y monto total con IVA.  
- Se valida la condición fiscal del cliente para calcular el IVA correspondiente.  
- La factura se almacena en la base de datos y puede visualizarse en un listado.  

---

### **HU-03: Visualización del Listado de Clientes y Facturas**
> **Como** operador,  
> **quiero** visualizar una lista de todos los clientes y sus facturas,  
> **para** consultar fácilmente el estado de las cuentas.

**Criterios de aceptación:**
- El sistema muestra una tabla con los clientes y sus facturas asociadas.  
- Permite filtrar por nombre, CUIT o fecha de facturación.  

---

### **HU-04: Facturación Masiva por Período**
> **Como** administrador del sistema,  
> **quiero** generar facturas en forma masiva para todos los clientes activos en un período,  
> **para** agilizar el proceso mensual de facturación.

**Criterios de aceptación:**
- Se selecciona un período (mes/año).  
- El sistema genera automáticamente las facturas correspondientes a cada cliente.  
- Se notifican los resultados del proceso (éxitos y errores).  

---

### **HU-05: Registro de Pagos**
> **Como** operador,  
> **quiero** registrar los pagos recibidos de los clientes,  
> **para** mantener actualizado el estado de las facturas.

**Criterios de aceptación:**
- Se puede registrar un pago indicando fecha, monto y medio de pago.  
- El sistema actualiza el estado de la factura a “Pagada”.  
- Se pueden consultar los pagos realizados por cliente.

---

### **HU-06: Anulación de Facturas**
> **Como** administrador,  
> **quiero** poder anular una factura emitida,  
> **para** corregir errores o cancelaciones.

**Criterios de aceptación:**
- La factura anulada cambia su estado a “Anulada”.  
- No puede editarse ni contabilizarse una factura anulada.  
- Se mantiene un registro del motivo de anulación.  

---

### **HU-07: Refactorización y Mejora de Usabilidad**
> **Como** desarrollador,  
> **quiero** optimizar el código y mejorar la interfaz,  
> **para** lograr un sistema más mantenible y fácil de usar.

**Criterios de aceptación:**
- Se mejora la estructura del código siguiendo principios SOLID.  
- Se corrigen errores detectados en iteraciones previas.  
- Se aplican mejoras visuales y de usabilidad en la interfaz de usuario.
