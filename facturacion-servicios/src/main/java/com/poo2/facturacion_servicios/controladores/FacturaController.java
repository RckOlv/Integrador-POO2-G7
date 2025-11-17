package com.poo2.facturacion_servicios.controladores;

import com.poo2.facturacion_servicios.servicios.AnulacionRequest;
import com.poo2.facturacion_servicios.modelos.NotaDeCredito;
import com.poo2.facturacion_servicios.servicios.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/facturas") // Endpoint base
public class FacturaController {

    private final FacturaService facturaService;

    // Inyección de dependencias por constructor
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    /**
     * Endpoint para anular una factura.
     * Cumple los criterios: 
     * 1. Selecciona una factura (con el {id} en la URL).
     * 2. Pide un motivo (con el @RequestBody).
     */
    @PostMapping("/{id}/anular")
    public ResponseEntity<?> anularFactura(
            @PathVariable Long id,
            @RequestBody AnulacionRequest request) {
        
        try {
            // Llama a la lógica de negocio en el servicio
            NotaDeCredito notaDeCredito = facturaService.anularFactura(id, request.getMotivo());
            
            // Si todo sale bien, devuelve 200 OK con la Nota de Crédito creada
            return ResponseEntity.ok(notaDeCredito);
        
        } catch (EntityNotFoundException e) {
            // Si la factura o cuenta no se encuentra
            return ResponseEntity.notFound().build();
        
        } catch (IllegalStateException e) {
            // Si la factura ya estaba anulada
            return ResponseEntity.badRequest().body(e.getMessage());
        
        } catch (Exception e) {
            // Error genérico del servidor
            return ResponseEntity.status(500).body("Error interno al anular la factura.");
        }
    }
}
