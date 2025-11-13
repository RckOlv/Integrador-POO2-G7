package com.poo2.facturacion_servicios.servicios;

import com.poo2.facturacion_servicios.modelos.Servicio;
import java.util.List;

public interface ServicioService {

    // (Criterio: "Puedo crear un Servicio")
    Servicio crearServicio(Servicio servicio);

    // (Criterio: "Puedo ver un listado")
    List<Servicio> listarServicios();

    // (Criterio: "Puedo modificar")
    Servicio actualizarServicio(Long id, Servicio detallesServicio);

    // --- Métodos auxiliares para un CRUD completo ---

    // (Necesario para actualizar y para la HU-04)
    Servicio buscarServicioPorId(Long id);

    // (CRUD completo)
    void eliminarServicio(Long id);
}