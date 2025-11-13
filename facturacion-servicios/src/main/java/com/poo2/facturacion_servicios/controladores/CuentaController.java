package com.poo2.facturacion_servicios.controladores;

import org.springframework.web.bind.annotation.*;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.EstadoCuenta;
import com.poo2.facturacion_servicios.servicios.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @PutMapping("/{id}/estado")
    public Cuenta cambiarEstadoCuenta(
            @PathVariable Long id,
            @RequestParam EstadoCuenta nuevoEstado) {
        return service.cambiarEstadoCuenta(id, nuevoEstado);
    }
}
