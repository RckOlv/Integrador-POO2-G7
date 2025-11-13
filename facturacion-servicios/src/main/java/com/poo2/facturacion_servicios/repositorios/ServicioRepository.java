package com.poo2.facturacion_servicios.repositorios;

import com.poo2.facturacion_servicios.modelos.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Buena práctica, aunque JpaRepository ya lo infiere
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
    // ¡No necesitas escribir nada más!
    // JpaRepository ya nos da mágicamente:
    // .save() -> (Crear y Actualizar)
    // .findAll() -> (Listar todos)
    // .findById() -> (Buscar uno)
    // .deleteById() -> (Eliminar)
}