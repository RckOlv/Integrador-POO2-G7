package com.poo2.facturacion_servicios.controladores;

import com.poo2.facturacion_servicios.modelos.Servicio; // Importamos el Modelo
import com.poo2.facturacion_servicios.servicios.ServicioService; // Importamos el Servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importamos todas las anotaciones web

import java.util.List;
import java.util.NoSuchElementException; // Para manejar errores

@RestController // Anotación clave: le dice a Spring que esto es un Controlador REST
@RequestMapping("/api/v1/servicios") // Define la URL base para todos los métodos de esta clase
public class ServicioController {

    // Inyectamos el Servicio que creamos en el Paso 5
    @Autowired
    private ServicioService servicioService;

    // --- Criterio HU-03: "Puedo crear un Servicio" ---
    // Mapea a: POST http://localhost:8080/api/v1/servicios
    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        Servicio nuevoServicio = servicioService.crearServicio(servicio);
        // Devolvemos 201 CREATED, que es la buena práctica REST para un POST
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    // --- Criterio HU-03: "Puedo ver un listado de todos los servicios ofrecidos" ---
    // Mapea a: GET http://localhost:8080/api/v1/servicios
    @GetMapping
    public ResponseEntity<List<Servicio>> listarServicios() {
        List<Servicio> servicios = servicioService.listarServicios();
        return ResponseEntity.ok(servicios); // Devuelve 200 OK
    }

    // --- Criterio HU-03: "Puedo modificar el Monto Base o la Alícuota" ---
    // Mapea a: PUT http://localhost:8080/api/v1/servicios/5 (donde 5 es el ID)
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio detallesServicio) {
        Servicio servicioActualizado = servicioService.actualizarServicio(id, detallesServicio);
        return ResponseEntity.ok(servicioActualizado);
    }

    // --- Métodos auxiliares del CRUD (opcionales pero recomendados) ---

    // Mapea a: GET http://localhost:8080/api/v1/servicios/5
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> buscarServicioPorId(@PathVariable Long id) {
        Servicio servicio = servicioService.buscarServicioPorId(id);
        return ResponseEntity.ok(servicio);
    }

    // Mapea a: DELETE http://localhost:8080/api/v1/servicios/5
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        // Devolvemos 204 NO CONTENT, buena práctica para un DELETE exitoso
        return ResponseEntity.noContent().build();
    }

    // --- Manejo de Excepciones ---
    // Un "Handler" para cuando el Servicio no existe (NoSuchElementException)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        // Devuelve un 404 NOT FOUND con el mensaje de error del servicio
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}