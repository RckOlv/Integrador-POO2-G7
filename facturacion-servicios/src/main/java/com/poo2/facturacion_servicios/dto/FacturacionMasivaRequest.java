package com.poo2.facturacion_servicios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacturacionMasivaRequest {
	private int mes;
    private int anio;
}
