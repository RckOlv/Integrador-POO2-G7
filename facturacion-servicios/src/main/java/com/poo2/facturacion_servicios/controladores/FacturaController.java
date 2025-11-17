package com.poo2.facturacion_servicios.controladores;

// CORREGIDO: Importa el DTO desde la ubicación correcta
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poo2.facturacion_servicios.controladores.dto.AnulacionRequest;
import com.poo2.facturacion_servicios.controladores.dto.FacturaIndividualRequest;
import com.poo2.facturacion_servicios.modelos.Factura;
import com.poo2.facturacion_servicios.modelos.NotaDeCredito;
import com.poo2.facturacion_servicios.servicios.FacturacionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/facturas") // Endpoint base
public class FacturaController {

    private final FacturacionService facturacionService;

    // Inyección de dependencias por constructor
    public FacturaController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }

    /**
     * Endpoint para HU-05: Facturación Masiva (T-05.5)
     * (Tu Tarea)
     */
    @PostMapping("/masiva")
    public ResponseEntity<String> ejecutarFacturacionMasiva(
            @RequestParam int mes,
            @RequestParam int anio) {
        try {
            String resultado = facturacionService.ejecutarFacturacionMasiva(mes, anio);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ejecutar la facturación masiva: " + e.getMessage());
        }
    }

    /**
     * Endpoint para HU-06: Factura Individual (T-06.3)
     */
    @PostMapping("/individual")
    public ResponseEntity<Factura> crearFacturaIndividual(
            @Valid @RequestBody FacturaIndividualRequest request) {
        try {
            Factura facturaCreada = facturacionService.crearFacturaIndividual(request);
            return new ResponseEntity<>(facturaCreada, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Endpoint para HU-07: Anulación de Factura (T-07.4)
     * (El que venía del pull de main)
     */
    @PostMapping("/{id}/anular")
    public ResponseEntity<?> anularFactura(
            @PathVariable Long id,
            @Valid @RequestBody AnulacionRequest request) { // <-- CORREGIDO (usa @Valid)
        
        try {
            // Llama a la lógica de negocio en el servicio
            NotaDeCredito notaDeCredito = facturacionService.anularFactura(id, request.getMotivo());
            
            // Si todo sale bien, devuelve 200 OK con la Nota de Crédito creada
            return ResponseEntity.ok(notaDeCredito);
        
        } catch (EntityNotFoundException e) {
            // Si la factura o cuenta no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        
        } catch (IllegalStateException e) {
            // Si la factura ya estaba anulada
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        
        } catch (Exception e) {
            // Error genérico del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al anular la factura: " + e.getMessage());
        }
    }
}