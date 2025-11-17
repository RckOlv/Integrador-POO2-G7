package com.poo2.facturacion_servicios.servicios;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnulacionRequest {
    // Criterio de Aceptación: Se debe pedir un motivo de anulación.
    private String motivo;
}