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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotaDeCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Este 'id' es de la NotaDeCredito (está bien)

    @Column(nullable = false)
    private BigDecimal importe; 

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false, length = 500) 
    private String motivoAnulacion; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; 

    // Criterio: Relación 1 a 1 con la factura que anula
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_anulada_id", // Nombre de la columna FK
                
                
                referencedColumnName = "idFactura", // Apunta al 'idFactura' de Factura.java
                
                unique = true, 
                nullable = false) 
    private Factura facturaOriginal;
}