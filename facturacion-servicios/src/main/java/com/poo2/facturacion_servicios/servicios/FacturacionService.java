package com.poo2.facturacion_servicios.servicios;

import com.poo2.facturacion_servicios.dto.FacturaIndividualRequest;
import com.poo2.facturacion_servicios.modelos.Factura;
import com.poo2.facturacion_servicios.modelos.NotaDeCredito; // <-- AÑADIR IMPORT

public interface FacturacionService {

    /**
     * HU-06: Generación de Factura Individual (Ya estaba)
     */
    Factura crearFacturaIndividual(FacturaIndividualRequest request);

    /**
     * HU-05: Generación de Facturación Masiva (Tu Tarea)
     * @param mes El mes (1-12) a facturar.
     * @param anio El año (ej. 2025) a facturar.
     * @return Un resumen del proceso.
     */
    String ejecutarFacturacionMasiva(int mes, int anio); // <-- AÑADIR ESTE MÉTODO

    /**
     * HU-07: Anulación de Facturas (Del pull de main)
     * @param facturaId El ID de la factura a anular.
     * @param motivo El motivo de la anulación.
     * @return La Nota de Crédito generada.
     */
    NotaDeCredito anularFactura(long facturaId, String motivo); // <-- AÑADIR ESTE MÉTODO
}

