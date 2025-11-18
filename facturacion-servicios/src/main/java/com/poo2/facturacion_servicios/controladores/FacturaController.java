package com.poo2.facturacion_servicios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // <-- Importante
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo2.facturacion_servicios.dto.FacturaIndividualRequest;
import com.poo2.facturacion_servicios.dto.FacturacionMasivaRequest;
import com.poo2.facturacion_servicios.modelos.Factura;
import com.poo2.facturacion_servicios.servicios.FacturacionService;

import lombok.Data; // Si usas Lombok

@RestController
@RequestMapping("/api/v1/facturacion")
public class FacturaController {

    @Autowired
    private FacturacionService facturacionService;

    // --- HU-06: Facturación Individual ---
    @PostMapping("/individual")
    public ResponseEntity<Factura> crearFacturaIndividual(@RequestBody FacturaIndividualRequest request) {
        Factura nuevaFactura = facturacionService.crearFacturaIndividual(request);
        return ResponseEntity.ok(nuevaFactura);
    }

    // --- HU-05: Facturación Masiva  ---
    @PostMapping("/masiva")
    public ResponseEntity<?> facturacionMasiva(@RequestBody FacturacionMasivaRequest request) {
        try {
            // Llamamos al servicio pasándole mes y año
            facturacionService.ejecutarFacturacionMasiva(request.getMes(), request.getAnio());
            
            // Devolvemos un mensaje de éxito en JSON
            return ResponseEntity.ok()
                .body("{\"mensaje\": \"Proceso de facturación masiva finalizado correctamente.\"}");
        
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }


}