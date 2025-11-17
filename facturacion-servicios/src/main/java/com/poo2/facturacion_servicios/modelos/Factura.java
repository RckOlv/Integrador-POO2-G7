package com.poo2.facturacion_servicios.modelos;

import java.math.BigDecimal;
import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;

import com.poo2.facturacion_servicios.enums.EstadoFactura; 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura; // <-- Actualizado (según diagrama)

    @ManyToOne
    private Cuenta cuenta;

    @Column(nullable = false)
    private LocalDate fechaEmision; // <-- Añadido (según diagrama)

    @Column(nullable = false)
    private String periodoFacturado; // <-- Añadido (según diagrama, ej: "10/2025")

    // RNF-03: Totales también deben ser BigDecimal
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal; // <-- Añadido (según diagrama)

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalIVA; // <-- Añadido (según diagrama)

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total; // <-- Añadido (según diagrama)

    @Enumerated(EnumType.STRING) // Guardar el enum como "PENDIENTE", "PAGADA", etc.
    @Column(nullable = false)
    private EstadoFactura estado; // <-- Corregido (de String a Enum)

    // Relación con Items (ya estaba, pero ahora usa ItemFactura corregido)
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemFactura> items = new ArrayList<>();

    // (Relación con NotaDeCredito se añadirá en HU-07)
    // @OneToOne(mappedBy = "facturaAnulada")
    // private NotaDeCredito notaDeCredito;

    /**
     * Método helper para sincronizar la relación bidireccional
     */
    public void addItem(ItemFactura item) {
        this.items.add(item);
        item.setFactura(this);
    }
}