package com.poo2.facturacion_servicios.servicios.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.poo2.facturacion_servicios.enums.EstadoCuenta;
import com.poo2.facturacion_servicios.modelos.Cliente;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.repositorios.ClienteRepository;
import com.poo2.facturacion_servicios.servicios.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Esta es la implementación de la lógica de negocio combinada de HU-01 y HU-02.
     * @Transactional asegura que si algo falla (ej. al guardar la cuenta),
     * toda la operación (incluido el cliente) se revierte (rollback).
     */
    @Override
    @Transactional // <-- Muy importante para operaciones de múltiples pasos
    public Cliente crearCliente(Cliente cliente) throws RuntimeException {
        // Criterio HU-01: "El sistema valida que el CUIT no esté duplicado."
        if (clienteRepository.existsByCuit(cliente.getCuit())) {
            throw new RuntimeException("Error: El CUIT " + cliente.getCuit() + " ya está registrado.");
        }

        // Criterio HU-02: "Al crear un Cliente (HU-01), se crea su Cuenta asociada en estado 'ACTIVA'."
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setEstado(EstadoCuenta.ACTIVA);
        
        // El saldo inicial será BigDecimal.ZERO, pero RNF-03 pide que esté en el modelo.
        // Asegúrate de que la clase Cuenta tenga el atributo 'saldo' BigDecimal.
        // Si no lo tiene, deberás añadirlo. Asumiré que lo tiene.
        // nuevaCuenta.setSaldo(BigDecimal.ZERO); // Descomentar cuando 'saldo' exista en Cuenta.java

        // Establecemos la relación bidireccional
        cliente.setCuenta(nuevaCuenta);
        nuevaCuenta.setCliente(cliente);

        // Guardamos el Cliente.
        // Gracias a @OneToOne(cascade = CascadeType.ALL) en Cliente.java,
        // la Cuenta se guardará automáticamente en la misma transacción.
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
        // 1. Encontrar el cliente existente
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con ID: " + id));

        // 2. Validar si el CUIT cambió y si el nuevo CUIT ya existe
        if (!clienteExistente.getCuit().equals(clienteDetalles.getCuit())) {
            if (clienteRepository.existsByCuit(clienteDetalles.getCuit())) {
                throw new RuntimeException("Error: El CUIT " + clienteDetalles.getCuit() + " ya pertenece a otro cliente.");
            }
        }

        // 3. Actualizar los campos (no se actualiza la cuenta)
        clienteExistente.setRazonSocial(clienteDetalles.getRazonSocial());
        clienteExistente.setCuit(clienteDetalles.getCuit());
        clienteExistente.setDomicilio(clienteDetalles.getDomicilio());
        clienteExistente.setEmail(clienteDetalles.getEmail());
        clienteExistente.setCondicionIVA(clienteDetalles.getCondicionIVA());

        // 4. Guardar
        return clienteRepository.save(clienteExistente);
    }

    @Override
    @Transactional(readOnly = true) // readOnly = true optimiza las consultas de solo lectura
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }
}