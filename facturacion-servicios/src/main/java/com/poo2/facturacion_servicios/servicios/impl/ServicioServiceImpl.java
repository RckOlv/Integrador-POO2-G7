package com.poo2.facturacion_servicios.servicios.impl;

import com.poo2.facturacion_servicios.modelos.Servicio; // Importamos el Modelo
import com.poo2.facturacion_servicios.repositorios.ServicioRepository; // Importamos el Repositorio
import com.poo2.facturacion_servicios.servicios.ServicioService; // Importamos la Interfaz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException; // Para manejar IDs que no existen

@Service // ¡Clave! Le dice a Spring que esta clase es un Servicio
public class ServicioServiceImpl implements ServicioService {

    // Inyección de Dependencias: Spring nos "pasa" el repositorio que creamos
    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio crearServicio(Servicio servicio) {
        // En este caso simple, solo llamamos al repositorio
        return servicioRepository.save(servicio);
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio buscarServicioPorId(Long id) {
        // findById devuelve un "Optional". Si no lo encuentra, lanzamos un error.
        return servicioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Servicio no encontrado con ID: " + id));
    }

    @Override
    public Servicio actualizarServicio(Long id, Servicio detallesServicio) {
        // --- Aquí está la lógica de negocio de la HU-03 ---

        // 1. Buscamos el servicio existente
        Servicio servicioExistente = buscarServicioPorId(id); // Reutilizamos nuestro método

        // 2. Actualizamos solo los campos que nos interesan
        servicioExistente.setNombre(detallesServicio.getNombre());
        servicioExistente.setMontoBase(detallesServicio.getMontoBase());
        servicioExistente.setAlicuotaIVA(detallesServicio.getAlicuotaIVA());

        // 3. Guardamos el objeto *existente* ya modificado
        return servicioRepository.save(servicioExistente);
    }

    @Override
    public void eliminarServicio(Long id) {
        // Antes de borrar, podríamos validar que este servicio no esté
        // siendo usado por ningún cliente (HU-04), pero por ahora lo dejamos simple.
        servicioRepository.deleteById(id);
    }
}