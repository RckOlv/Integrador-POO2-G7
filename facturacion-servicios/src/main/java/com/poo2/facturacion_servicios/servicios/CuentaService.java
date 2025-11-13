package com.poo2.facturacion_servicios.servicios;

import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.EstadoCuenta;
import com.poo2.facturacion_servicios.repositorios.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository repo;

    public Cuenta cambiarEstadoCuenta(Long id, EstadoCuenta nuevoEstado) {
        Cuenta cuenta = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setEstado(nuevoEstado);
        return repo.save(cuenta);
    }
}
