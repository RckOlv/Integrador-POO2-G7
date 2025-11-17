package com.poo2.facturacion_servicios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo2.facturacion_servicios.modelos.ItemFactura;

public interface ItemFacturaRepository extends JpaRepository<ItemFactura, Long> {
}
