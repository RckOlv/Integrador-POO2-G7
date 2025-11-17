package com.poo2.facturacion_servicios.controladores.dto;

import com.poo2.facturacion_servicios.enums.CondicionIVA;
import com.poo2.facturacion_servicios.enums.EstadoCuenta;
import com.poo2.facturacion_servicios.modelos.Cliente;

import lombok.Data;


@Data
public class ClienteDTO {

    private Long idCliente;
    private String razonSocial;
    private String cuit;
    private String domicilio;
    private String email;
    private CondicionIVA condicionIVA;
    
   
    private Long idCuenta;
    private EstadoCuenta estadoCuenta;


    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.razonSocial = cliente.getRazonSocial();
        this.cuit = cliente.getCuit();
        this.domicilio = cliente.getDomicilio();
        this.email = cliente.getEmail();
        this.condicionIVA = cliente.getCondicionIVA();
        
        // Mapeo de la cuenta asociada
        if (cliente.getCuenta() != null) {
            this.idCuenta = cliente.getCuenta().getId();
            this.estadoCuenta = cliente.getCuenta().getEstado();
        }
    }
}