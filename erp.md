
## 3. Requisitos Funcionales (RF)

A continuación, se detallan los **requisitos funcionales** derivados de las historias de usuario planificadas en las iteraciones del proyecto.

### Iteración 1

#### **RF-01: Registro y Gestión de Clientes**
El sistema debe permitir registrar, modificar, consultar y eliminar datos de clientes.
- Debe incluir campos de nombre, apellido, CUIT/CUIL, dirección, teléfono, correo y condición fiscal.
- Debe validar que el CUIT/CUIL sea único.

#### **RF-02: Gestión de Cuentas**
El sistema debe permitir crear, editar y eliminar cuentas asociadas a los clientes.
- Cada cuenta debe estar vinculada a un cliente existente.
- Debe mostrar el estado actual de la cuenta (activa/inactiva).

#### **RF-03: Condición Fiscal**
El sistema debe permitir registrar y modificar la condición fiscal de cada cliente.
- Las condiciones fiscales posibles incluyen: Responsable Inscripto, Monotributista, Exento, Consumidor Final.

#### **RF-04: Autenticación de Usuarios**
El sistema debe permitir que los usuarios accedan mediante autenticación (login).
- Debe distinguir entre roles: Administrador, Operador y Supervisor.

#### **RF-05: Generación de Factura Individual**
El sistema debe permitir la creación de facturas individuales.
- Debe incluir la selección del cliente, los servicios facturados, montos e impuestos aplicables.
- Debe validar que todos los datos requeridos estén completos.

#### **RF-06: Listado de Clientes y Cuentas**
El sistema debe ofrecer un listado filtrable de clientes y cuentas.
- Debe permitir filtrar por nombre, CUIT, o estado de cuenta.
- Debe poder exportarse a PDF o Excel.

#### **RF-07: Anulación de Facturas**
El sistema debe permitir anular facturas generadas.
- Solo los usuarios con rol “Supervisor” o “Administrador” pueden realizar la anulación.
- El sistema debe registrar la fecha y motivo de la anulación.

---

### Iteración 2

#### **RF-08: Facturación Masiva por Período**
El sistema debe permitir ejecutar un proceso de facturación masiva.
- El usuario debe poder seleccionar un período (mensual o bimestral).
- El sistema debe generar una factura para cada cliente activo.

#### **RF-09: Registro de Pagos**
El sistema debe permitir registrar pagos asociados a facturas.
- Debe registrar la fecha, monto, medio de pago y número de comprobante.
- El sistema debe actualizar automáticamente el estado de la factura.

#### **RF-10: Reporte de Facturación**
El sistema debe generar reportes de facturación por período.
- Debe permitir filtrar por cliente, rango de fechas o tipo de factura.
- Los reportes deben poder exportarse a PDF o Excel.

#### **RF-11: Control de Pagos Pendientes**
El sistema debe listar todas las facturas pendientes de pago.
- Debe mostrar cliente, importe total y días de demora.
- Debe permitir el envío de recordatorios de pago.

#### **RF-12: Auditoría de Operaciones**
El sistema debe registrar todas las operaciones realizadas.
- Debe almacenar usuario, acción, fecha y hora.
- Solo los administradores pueden acceder al registro de auditoría.

#### **RF-13: Configuración de IVA**
El sistema debe aplicar automáticamente el IVA según la condición fiscal del cliente.
- El porcentaje de IVA debe ser configurable por el administrador.
- El sistema debe calcular el monto neto y total en cada factura.

#### **RF-14: Seguridad y Control de Acceso**
El sistema debe implementar un control de acceso basado en roles.
- Los administradores pueden gestionar usuarios y permisos.
- Los operadores solo podrán acceder a módulos de facturación y pagos.

---

## 4. Requisitos No Funcionales (RNF)

### **RNF-01: Cumplimiento Legal**
El sistema debe cumplir con la legislación fiscal argentina vigente sobre facturación e IVA.

### **RNF-02: Usabilidad**
La interfaz debe ser clara, intuitiva y accesible desde navegadores modernos.

### **RNF-03: Rendimiento**
Las operaciones de facturación masiva deben completarse en menos de 30 segundos para hasta 1000 registros.

### **RNF-04: Seguridad**
Los datos sensibles deben almacenarse cifrados y el sistema debe utilizar HTTPS para todas las comunicaciones.

### **RNF-05: Mantenibilidad**
El código fuente debe estructurarse según buenas prácticas de desarrollo (MVC, modularidad, documentación interna).

### **RNF-06: Portabilidad**
El sistema debe poder implementarse en servidores Linux o Windows con soporte de PostgreSQL.

### **RNF-07: Disponibilidad**
El sistema debe garantizar una disponibilidad del 99% en horario laboral.
