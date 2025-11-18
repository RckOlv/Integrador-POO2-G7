package com.poo2.facturacion_servicios.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // <-- Para activar las validaciones del DTO
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo2.facturacion_servicios.dto.ClienteDTO;
import com.poo2.facturacion_servicios.dto.CrearClienteRequest;
import com.poo2.facturacion_servicios.modelos.Cliente;
import com.poo2.facturacion_servicios.servicios.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes") // URL base para Clientes
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Endpoint para T-01.4 (POST) - Criterio HU-01: "Puedo crear un Cliente"
     * Usa el DTO (T-01.5) y las validaciones.
     */
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody CrearClienteRequest request) {
        
        // 1. Convertir DTO a Entidad (Modelo)
        Cliente nuevoCliente = new Cliente(
                request.getRazonSocial(),
                request.getCuit(),
                request.getDomicilio(),
                request.getEmail(),
                request.getCondicionIVA()
        );

        // 2. Llamar al servicio (que valida CUIT y crea la Cuenta)
        Cliente clienteGuardado = clienteService.crearCliente(nuevoCliente);

        // 3. Convertir Entidad guardada a DTO de respuesta
        ClienteDTO respuestaDTO = new ClienteDTO(clienteGuardado);

        return new ResponseEntity<>(respuestaDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint para T-01.4 (GET) - Criterio: "Puedo ver los clientes"
     */
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        
        // Convertir la lista de Entidades a lista de DTOs
        List<ClienteDTO> dtos = clientes.stream()
                .map(ClienteDTO::new) // Llama al constructor que hicimos (Cliente -> ClienteDTO)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(dtos);
    }

    /**
     * Endpoint para T-01.4 (PUT) - Criterio HU-01: "Puedo modificar los datos"
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, 
                                                        @Valid @RequestBody CrearClienteRequest request) {
        
        // (La lógica de conversión es similar al POST)
        Cliente clienteDetalles = new Cliente(
                request.getRazonSocial(),
                request.getCuit(),
                request.getDomicilio(),
                request.getEmail(),
                request.getCondicionIVA()
        );

        Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteDetalles);
        
        return ResponseEntity.ok(new ClienteDTO(clienteActualizado));
    }

    /**
     * Endpoint para T-01.4 (GET por ID) - Método auxiliar
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        
        return clienteService.obtenerClientePorId(id)
                .map(cliente -> ResponseEntity.ok(new ClienteDTO(cliente))) // Si lo encuentra, lo convierte a DTO y OK (200)
                .orElse(ResponseEntity.notFound().build()); // Si no, devuelve 404
    }


}
