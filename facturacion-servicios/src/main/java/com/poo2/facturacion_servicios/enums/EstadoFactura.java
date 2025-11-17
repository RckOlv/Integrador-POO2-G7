package com.poo2.facturacion_servicios.enums;

public enum EstadoFactura {
    PENDIENTE, // Recién creada, en espera de pago
    PAGADA,    // El pago se ha registrado
    ANULADA    // Se ha generado una Nota de Crédito (HU-07)
}
