package com.poo2.facturacion_servicios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poo2.facturacion_servicios.modelos.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Criterio HU-01: "El sistema valida que el CUIT no esté duplicado."
     * Usaremos este método para verificar si ya existe un cliente con ese CUIT.
     * * @param cuit El CUIT a verificar.
     * @return true si ya existe, false si no.
     */
    boolean existsByCuit(String cuit);

    /**
     * Método opcional pero útil para buscar un cliente por su CUIT.
     * * @param cuit El CUIT a buscar.
     * @return Un Optional que contiene al Cliente si se encuentra.
     */
    Optional<Cliente> findByCuit(String cuit);
    
}