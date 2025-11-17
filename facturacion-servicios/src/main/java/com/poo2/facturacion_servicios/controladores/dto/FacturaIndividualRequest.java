package com.poo2.facturacion_servicios.controladores.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class FacturaIndividualRequest {

    private Long cuentaId;
    private List<ItemDTO> items;

}

