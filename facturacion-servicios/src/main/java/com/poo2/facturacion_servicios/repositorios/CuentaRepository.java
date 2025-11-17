package com.poo2.facturacion_servicios.repositorios;

import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByCliente(Cliente cliente);
}