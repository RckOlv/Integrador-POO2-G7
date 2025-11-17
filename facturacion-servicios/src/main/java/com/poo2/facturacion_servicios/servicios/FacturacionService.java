package com.poo2.facturacion_servicios.servicios;

import com.poo2.facturacion_servicios.controladores.dto.FacturaIndividualRequest;
import com.poo2.facturacion_servicios.modelos.Factura;

public interface FacturacionService {
    Factura crearFacturaIndividual(FacturaIndividualRequest request);
}

