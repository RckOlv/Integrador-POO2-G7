package com.poo2.facturacion_servicios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poo2.facturacion_servicios.modelos.ServicioContratado;

import jakarta.transaction.Transactional;


public interface ServicioContratadoRepository extends JpaRepository<ServicioContratado, Long> {

    List<ServicioContratado> findByCuentaId(Long cuentaId);

   @Modifying
@Transactional
@Query("DELETE FROM ServicioContratado sc WHERE sc.cuenta.id = :idCuenta AND sc.servicio.id = :idServicio")
void eliminarRelacion(@Param("idCuenta") Long idCuenta, @Param("idServicio") Long idServicio);
    
}
