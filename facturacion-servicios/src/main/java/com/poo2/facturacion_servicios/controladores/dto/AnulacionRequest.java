package com.poo2.facturacion_servicios.controladores.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * DTO (T-07.4) para recibir el motivo de anulación de una factura (HU-07).
 */
@Data
public class AnulacionRequest {

    @NotEmpty(message = "El motivo de anulación no puede estar vacío")
    private String motivo;
}
