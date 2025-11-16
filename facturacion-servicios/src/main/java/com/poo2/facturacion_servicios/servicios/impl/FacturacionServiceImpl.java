package com.poo2.facturacion_servicios.servicios.impl;

import com.poo2.facturacion_servicios.controladores.dto.FacturaIndividualRequest;
import com.poo2.facturacion_servicios.controladores.dto.ItemDTO;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.Factura;
import com.poo2.facturacion_servicios.modelos.ItemFactura;
import com.poo2.facturacion_servicios.repositorios.CuentaRepository;
import com.poo2.facturacion_servicios.repositorios.FacturaRepository;
import com.poo2.facturacion_servicios.repositorios.ItemFacturaRepository;
import com.poo2.facturacion_servicios.servicios.FacturacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturacionServiceImpl implements FacturacionService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ItemFacturaRepository itemFacturaRepository;

    @Override
    public Factura crearFacturaIndividual(FacturaIndividualRequest request) {

        Cuenta cuenta = cuentaRepository.findById(request.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Factura factura = new Factura();
        factura.setCuenta(cuenta);
        factura.setEstado("PENDIENTE");
        factura.setNumero("FAC-" + System.currentTimeMillis());

        factura = facturaRepository.save(factura);

        for (ItemDTO dto : request.getItems()) {
            ItemFactura item = new ItemFactura();
            item.setDescripcion(dto.getDescripcion());
            item.setMonto(dto.getMonto());
            item.setIva(dto.getIva());
            item.setFactura(factura);

            itemFacturaRepository.save(item);
            factura.addItem(item);
        }

        return factura;
    }
}

