package com.poo2.facturacion_servicios.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo2.facturacion_servicios.enums.EstadoCuenta;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.repositorios.CuentaRepository;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository repo;

    public Cuenta cambiarEstadoCuenta(@org.springframework.lang.NonNull Long id, EstadoCuenta nuevoEstado) {
        Cuenta cuenta = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setEstado(nuevoEstado);
        return repo.save(cuenta);
    }
}
