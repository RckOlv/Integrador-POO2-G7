package com.poo2.facturacion_servicios.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.poo2.facturacion_servicios.modelos.EstadoCuenta;
import com.poo2.facturacion_servicios.repositorios.EstadoCuentaRepository;

@Service
public class EstadoCuentaService {

    private final EstadoCuentaRepository estadoCuentaRepository;

    public EstadoCuentaService(EstadoCuentaRepository estadoCuentaRepository) {
        this.estadoCuentaRepository = estadoCuentaRepository;
    }

    public List<EstadoCuenta> listarEstados() {
        return estadoCuentaRepository.findAll();
    }

    public Optional<EstadoCuenta> obtenerPorId(Long id) {
        return estadoCuentaRepository.findById(id);
    }

    public EstadoCuenta guardar(EstadoCuenta estadoCuenta) {
        return estadoCuentaRepository.save(estadoCuenta);
    }

    public void eliminar(Long id) {
        estadoCuentaRepository.deleteById(id);
    }
}
