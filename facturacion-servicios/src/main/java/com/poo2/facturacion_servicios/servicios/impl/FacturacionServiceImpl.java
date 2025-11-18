package com.poo2.facturacion_servicios.servicios.impl;

// Imports EXPLÍCITOS para todas las HUs (05, 06, 07)
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poo2.facturacion_servicios.dto.FacturaIndividualRequest;
import com.poo2.facturacion_servicios.dto.ItemDTO;
import com.poo2.facturacion_servicios.enums.EstadoCuenta;
import com.poo2.facturacion_servicios.enums.EstadoFactura;
import com.poo2.facturacion_servicios.modelos.Cuenta;
import com.poo2.facturacion_servicios.modelos.Factura;
import com.poo2.facturacion_servicios.modelos.ItemFactura;
import com.poo2.facturacion_servicios.modelos.NotaDeCredito;
import com.poo2.facturacion_servicios.modelos.Servicio;
import com.poo2.facturacion_servicios.modelos.ServicioContratado;
import com.poo2.facturacion_servicios.repositorios.CuentaRepository; // Import Faltante
import com.poo2.facturacion_servicios.repositorios.FacturaRepository;
import com.poo2.facturacion_servicios.repositorios.ItemFacturaRepository;
import com.poo2.facturacion_servicios.repositorios.NotaDeCreditoRepository;
import com.poo2.facturacion_servicios.repositorios.ServicioContratadoRepository;
import com.poo2.facturacion_servicios.servicios.FacturacionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FacturacionServiceImpl implements FacturacionService {

    // --- Inyección de Repositorios ---
    @Autowired private FacturaRepository facturaRepository;
    @Autowired private ItemFacturaRepository itemFacturaRepository;
    @Autowired private CuentaRepository cuentaRepository;
    @Autowired private NotaDeCreditoRepository notaDeCreditoRepository;
    @Autowired private ServicioContratadoRepository servicioContratadoRepository;

    // Constante para cálculos de IVA
    private static final BigDecimal CIEN = new BigDecimal("100");

    /**
     * HU-05: Lógica de Facturación Masiva (T-05.4)
     */
    @Override
    @Transactional
    public String ejecutarFacturacionMasiva(int mes, int anio) {
        
        List<Cuenta> cuentasActivas = cuentaRepository.findByEstado(EstadoCuenta.ACTIVA);
        int facturasGeneradas = 0;
        String periodo = String.format("%02d/%d", mes, anio);

        for (Cuenta cuenta : cuentasActivas) {
            
            List<ServicioContratado> servicios = servicioContratadoRepository.findByCuentaId(cuenta.getId());

            if (servicios.isEmpty()) {
                continue; 
            }

            Factura factura = new Factura();
            factura.setCuenta(cuenta); 
            factura.setCliente(cuenta.getCliente()); // <-- Ahora esto funciona (Tarea 1)
            factura.setFechaEmision(LocalDate.now());
            factura.setPeriodoFacturado(periodo);
            factura.setEstado(EstadoFactura.PENDIENTE);
            
            BigDecimal subTotal = BigDecimal.ZERO;
            BigDecimal totalIVA = BigDecimal.ZERO;

            for (ServicioContratado sc : servicios) {
                Servicio servicio = sc.getServicio(); 
                
                BigDecimal montoBase = servicio.getMontoBase();
                BigDecimal alicuota = servicio.getAlicuotaIVA();
                
                BigDecimal ivaCalculado = montoBase.multiply(alicuota.divide(CIEN, 2, RoundingMode.HALF_UP));

                ItemFactura item = new ItemFactura(
                        servicio.getNombre(), 
                        montoBase,            
                        ivaCalculado          
                );
                
                factura.addItem(item); 

                subTotal = subTotal.add(montoBase);
                totalIVA = totalIVA.add(ivaCalculado);
            }

            factura.setSubTotal(subTotal);
            factura.setTotalIVA(totalIVA);
            factura.setTotal(subTotal.add(totalIVA));

            facturaRepository.save(factura);
            facturasGeneradas++;
        }

        return "Proceso de facturación masiva completado. Se generaron " + facturasGeneradas + " facturas para el período " + periodo;
    }

    /**
     * HU-06: Lógica de Factura Individual
     */
    @Override
    @Transactional
    public Factura crearFacturaIndividual(FacturaIndividualRequest request) {
        
        Cuenta cuenta = cuentaRepository.findById(request.getCuentaId())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada: " + request.getCuentaId()));

        Factura factura = new Factura();
        factura.setCuenta(cuenta);
        factura.setCliente(cuenta.getCliente()); // <-- Ahora esto funciona (Tarea 1)
        factura.setEstado(EstadoFactura.PENDIENTE); 
        factura.setFechaEmision(LocalDate.now()); 
        factura.setPeriodoFacturado("INDIVIDUAL"); 

        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalIVA = BigDecimal.ZERO;

        for (ItemDTO dto : request.getItems()) {
            
            BigDecimal monto = BigDecimal.valueOf(dto.getMonto());
            BigDecimal iva = BigDecimal.valueOf(dto.getIva());

            ItemFactura item = new ItemFactura();
            item.setDescripcion(dto.getDescripcion());
            item.setMontoItem(monto); 
            item.setIVACalculado(iva); 
            
            factura.addItem(item); 

            subTotal = subTotal.add(monto);
            totalIVA = totalIVA.add(iva);
        }
        
        factura.setSubTotal(subTotal);
        factura.setTotalIVA(totalIVA);
        factura.setTotal(subTotal.add(totalIVA));

        return facturaRepository.save(factura);
    }


    /**
     * HU-07: Lógica de Anulación
     */
    @Override
    @Transactional
    public NotaDeCredito anularFactura(long facturaId, String motivo) {
        
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con ID: " + facturaId));

        if (factura.getEstado() == EstadoFactura.ANULADA) {
            throw new IllegalStateException("La factura ya se encuentra anulada.");
        }

        factura.setEstado(EstadoFactura.ANULADA);
        facturaRepository.save(factura);

        NotaDeCredito notaDeCredito = new NotaDeCredito();
        notaDeCredito.setFacturaOriginal(factura);
        notaDeCredito.setCliente(factura.getCliente()); 
        notaDeCredito.setImporte(factura.getTotal()); 
        notaDeCredito.setMotivoAnulacion(motivo);
        notaDeCredito.setFechaEmision(LocalDate.now());
        
        NotaDeCredito notaGuardada = notaDeCreditoRepository.save(notaDeCredito);

        Cuenta cuenta = factura.getCuenta();
        
        BigDecimal saldoActual = cuenta.getSaldo();
        BigDecimal importeNc = notaGuardada.getImporte(); 
        BigDecimal nuevoSaldo = saldoActual.subtract(importeNc);
        
        cuenta.setSaldo(nuevoSaldo);
        
        cuentaRepository.save(cuenta); 

        return notaGuardada;
    }
}