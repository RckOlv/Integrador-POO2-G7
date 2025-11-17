package com.poo2.facturacion_servicios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poo2.facturacion_servicios.modelos.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Valida si ya existe un cliente con el mismo CUIT.
     */
    boolean existsByCuit(String cuit);

    /**
     * Busca un cliente por CUIT.
     */
    Optional<Cliente> findByCuit(String cuit);
}
