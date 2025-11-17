package com.poo2.facturacion_servicios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo2.facturacion_servicios.modelos.ServicioContratado;


public interface ServicioContratadoRepository extends JpaRepository<ServicioContratado, Long> {

    List<ServicioContratado> findByCuentaId(Long cuentaId);

    void deleteByCuentaIdAndServicioId(Long cuentaId, Long servicioId);
}
