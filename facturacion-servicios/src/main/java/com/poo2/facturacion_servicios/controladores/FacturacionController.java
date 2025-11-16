package com.poo2.facturacion_servicios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo2.facturacion_servicios.controladores.dto.FacturaIndividualRequest;
import com.poo2.facturacion_servicios.modelos.Factura;
import com.poo2.facturacion_servicios.servicios.FacturacionService;

@RestController
@RequestMapping("/facturacion")
public class FacturacionController {

    @Autowired
    private FacturacionService facturacionService;

    @PostMapping("/individual")
    public Factura crearFacturaIndividual(@RequestBody FacturaIndividualRequest request) {
        return facturacionService.crearFacturaIndividual(request);
    }
}

