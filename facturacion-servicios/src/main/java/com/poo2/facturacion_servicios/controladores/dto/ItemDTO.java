package com.poo2.facturacion_servicios.controladores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String descripcion;
    private Double monto;
    private Double iva;


}

