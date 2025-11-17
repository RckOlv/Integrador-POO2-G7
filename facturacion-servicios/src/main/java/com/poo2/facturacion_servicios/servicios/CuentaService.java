package com.poo2.facturacion_servicios.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo2.facturacion_servicios.enums.EstadoCuenta;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.Servicio;
import com.poo2.facturacion_servicios.modelos.ServicioContratado;
import com.poo2.facturacion_servicios.repositorios.CuentaRepository;
import com.poo2.facturacion_servicios.repositorios.ServicioContratadoRepository;
import com.poo2.facturacion_servicios.repositorios.ServicioRepository;

import jakarta.transaction.Transactional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository repo;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ServicioContratadoRepository servicioContratadoRepository;

    public Cuenta cambiarEstadoCuenta(Long id, EstadoCuenta nuevoEstado) {
        Cuenta cuenta = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setEstado(nuevoEstado);
        return repo.save(cuenta);
    }

    // Asignar servicio a una cuenta
    @Transactional
    public ServicioContratado asignarServicio(Long cuentaId, Long servicioId) {
        Cuenta cuenta = repo.findById(cuentaId)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Servicio servicio = servicioRepository.findById(servicioId)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        ServicioContratado sc = new ServicioContratado();
        sc.setCuenta(cuenta);
        sc.setServicio(servicio);
        sc.setFechaAlta(LocalDate.now());

        return servicioContratadoRepository.save(sc);
    }

    // Quitar servicio de una cuenta
    @Transactional
    public void quitarServicio(Long cuentaId, Long servicioId) {
        servicioContratadoRepository.deleteByCuentaIdAndServicioId(cuentaId, servicioId);
    }

    // Listar servicios contratados de una cuenta
    public List<ServicioContratado> listarServicios(Long cuentaId) {
        return servicioContratadoRepository.findByCuentaId(cuentaId);
    }

}
