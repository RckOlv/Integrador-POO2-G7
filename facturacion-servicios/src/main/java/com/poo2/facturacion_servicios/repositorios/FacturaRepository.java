package com.poo2.facturacion_servicios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo2.facturacion_servicios.modelos.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}

