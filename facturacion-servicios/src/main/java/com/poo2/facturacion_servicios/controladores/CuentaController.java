package com.poo2.facturacion_servicios.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poo2.facturacion_servicios.dto.AsignarServicioDTO;
import com.poo2.facturacion_servicios.enums.EstadoCuenta;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.ServicioContratado;
import com.poo2.facturacion_servicios.servicios.CuentaService;

@RestController
@RequestMapping("/api/v1/cuentas")
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

    //Asignar un servicio 
    @PostMapping("/{cuentaId}/servicios")
    public ResponseEntity<?> asignarServicio(
            @PathVariable Long cuentaId,
            @RequestBody AsignarServicioDTO dto) {
        
        ServicioContratado sc = service.asignarServicio(cuentaId, dto.getServicioId());
        return ResponseEntity.ok(sc);
    }

    //Quitar un servicio:
    @DeleteMapping("/{cuentaId}/servicios/{servicioId}")
    public ResponseEntity<?> quitarServicio(
            @PathVariable Long cuentaId,
            @PathVariable Long servicioId) {

        service.quitarServicio(cuentaId, servicioId);
        return ResponseEntity.noContent().build();
    }

    //Mostrar servicios contratados
    @GetMapping("/{cuentaId}/servicios")
    public List<ServicioContratado> listarServicios(@PathVariable Long cuentaId) {
        return service.listarServicios(cuentaId);
    }
}
