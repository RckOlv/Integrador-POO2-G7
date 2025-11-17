package com.poo2.facturacion_servicios.modelos;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemFactura; // <-- Actualizado (según diagrama)

    @Column(nullable = false)
    private String descripcion;


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montoItem; // <-- Actualizado (tipo y nombre)

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal IVACalculado; // <-- Actualizado (tipo y nombre)

    @ManyToOne
    private Factura factura;

    // Constructor simple para la HU-06 (Individual)
    // Nota: El 'IVACalculado' se espera que venga ya resuelto.
    public ItemFactura(String descripcion, BigDecimal montoItem, BigDecimal IVACalculado) {
        this.descripcion = descripcion;
        this.montoItem = montoItem;
        this.IVACalculado = IVACalculado;
    }

}
