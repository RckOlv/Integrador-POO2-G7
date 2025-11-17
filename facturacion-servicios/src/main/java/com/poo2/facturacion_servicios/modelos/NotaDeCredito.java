package com.poo2.facturacion_servicios.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notas_de_credito")
// Anotaciones de Lombok
@Getter                 // Genera todos los getters
@Setter                 // Genera todos los setters
@NoArgsConstructor      // Constructor vacío para JPA
@AllArgsConstructor     // Constructor con todos los campos
public class NotaDeCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal importe; // Criterio: Mismo importe que la factura

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false, length = 500) // Damos espacio para el motivo
    private String motivoAnulacion; // Criterio: Se debe pedir un motivo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; // Asociado al mismo cliente

    // Criterio: Relación 1 a 1 con la factura que anula
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_anulada_id", // Nombre de la columna FK
                referencedColumnName = "id", // Apunta al 'id' de la Factura
                unique = true, // Asegura que solo una NC puede anular una factura
                nullable = false) // Una NC siempre debe tener una factura original
    private Factura facturaOriginal;
}