package com.poo2.facturacion_servicios.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poo2.facturacion_servicios.modelos.NotaDeCredito;

@Repository
public interface NotaDeCreditoRepository extends JpaRepository<NotaDeCredito, Long> {
}
