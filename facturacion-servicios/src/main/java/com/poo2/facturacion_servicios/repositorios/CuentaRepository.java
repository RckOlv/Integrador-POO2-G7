package com.poo2.facturacion_servicios.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; // <-- AÑADIDO

import com.poo2.facturacion_servicios.enums.EstadoCuenta; // <-- AÑADIDO
import com.poo2.facturacion_servicios.modelos.Cliente; // <-- AÑADIDO
import com.poo2.facturacion_servicios.modelos.Cuenta; // <-- AÑADIDO

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    /**
     * Requerido por HU-05 (Facturacion masiva)
     */
    List<Cuenta> findByEstado(EstadoCuenta estado); // <-- AÑADIDO

    /**
     * Requerido por HU-07 (Anulación)
     */
    Optional<Cuenta> findByCliente(Cliente cliente); // <-- AÑADIDO
}