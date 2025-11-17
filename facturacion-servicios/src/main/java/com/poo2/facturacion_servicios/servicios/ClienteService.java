package com.poo2.facturacion_servicios.servicios;

import java.util.List;
import java.util.Optional;

import com.poo2.facturacion_servicios.modelos.Cliente;

/**
 * Interfaz para la lógica de negocio de la HU-01: Gestión de Clientes.
 */
public interface ClienteService {

    /**
     * Criterio HU-01: "Puedo crear un Cliente"
     * Criterio HU-02: "Al crear un Cliente (HU-01), se crea su Cuenta asociada en estado 'ACTIVA'."
     * Criterio HU-01: "El sistema valida que el CUIT no esté duplicado."
     *
     * @param cliente El cliente con los datos a crear.
     * @return El cliente guardado (incluyendo su nueva cuenta).
     * @throws RuntimeException si el CUIT ya existe.
     */
    Cliente crearCliente(Cliente cliente) throws RuntimeException;

    /**
     * Criterio HU-01: "Puedo modificar los datos de un Cliente."
     *
     * @param id El ID del cliente a modificar.
     * @param clienteDetalles Los nuevos datos para el cliente.
     * @return El cliente actualizado.
     */
    Cliente actualizarCliente(Long id, Cliente clienteDetalles);

    /**
     * Método auxiliar para listar todos los clientes.
     *
     * @return Una lista de todos los clientes.
     */
    List<Cliente> listarClientes();

    /**
     * Método auxiliar para buscar un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return Un Optional conteniendo al cliente si se encuentra.
     */
    Optional<Cliente> obtenerClientePorId(Long id);
}
