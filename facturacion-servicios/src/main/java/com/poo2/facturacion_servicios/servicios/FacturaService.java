package com.poo2.facturacion_servicios.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poo2.facturacion_servicios.enums.EstadoFactura;
import com.poo2.facturacion_servicios.modelos.Cliente;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.Factura;
import com.poo2.facturacion_servicios.modelos.NotaDeCredito;
import com.poo2.facturacion_servicios.repositorios.ClienteRepository;
import com.poo2.facturacion_servicios.repositorios.CuentaRepository;
import com.poo2.facturacion_servicios.repositorios.FacturaRepository;
import com.poo2.facturacion_servicios.repositorios.NotaDeCreditoRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final NotaDeCreditoRepository notaDeCreditoRepository;
    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository; 

    // Actualiza el constructor
    public FacturaService(FacturaRepository facturaRepository,
                          NotaDeCreditoRepository notaDeCreditoRepository,
                          ClienteRepository clienteRepository,
                          CuentaRepository cuentaRepository) { 
        this.facturaRepository = facturaRepository;
        this.notaDeCreditoRepository = notaDeCreditoRepository;
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository; 
    }

    @Transactional
    public NotaDeCredito anularFactura(long facturaId, String motivo) {
        
        // 1 y 2. Buscar y validar factura (sin cambios)
        Factura factura = facturaRepository.findById(facturaId)
            .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con ID: " + facturaId));

        if (factura.getEstado() == EstadoFactura.ANULADA) {
            throw new IllegalStateException("La factura ya se encuentra anulada.");
        }

        // 3. Marcar factura como ANULADA (sin cambios)
        factura.setEstado(EstadoFactura.ANULADA);
        facturaRepository.save(factura);

        // 4. Crear Nota de Crédito (sin cambios)
        NotaDeCredito notaDeCredito = new NotaDeCredito();
        notaDeCredito.setFacturaOriginal(factura);
        notaDeCredito.setCliente(factura.getCliente());
        notaDeCredito.setImporte(factura.getImporte());
        notaDeCredito.setMotivoAnulacion(motivo);
        notaDeCredito.setFechaEmision(LocalDate.now());
        
        NotaDeCredito notaGuardada = notaDeCreditoRepository.save(notaDeCredito);

        // 5. Impactar saldo 
        
        // Obtenemos el cliente desde la factura
        Cliente cliente = factura.getCliente();

        // Buscamos la cuenta asociada a ese cliente
        // (Asumimos que una cuenta siempre existe para un cliente)
        Cuenta cuenta = cuentaRepository.findByCliente(cliente)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la cuenta para el cliente ID: " + cliente.getIdCliente()));
        
        // Asumimos que el saldo es la deuda. Nota de Credito disminuye la deuda.
        BigDecimal saldoActual = cuenta.getSaldo();
        BigDecimal importeNc = notaDeCredito.getImporte();
        BigDecimal nuevoSaldo = saldoActual.subtract(importeNc);
        
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta); // Guardamos la entidad Cuenta, no el Cliente

        return notaGuardada;
    }
}