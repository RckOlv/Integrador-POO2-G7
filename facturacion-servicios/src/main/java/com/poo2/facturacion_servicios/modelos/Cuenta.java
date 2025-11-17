package com.poo2.facturacion_servicios.modelos;

import java.math.BigDecimal;

import com.poo2.facturacion_servicios.enums.EstadoCuenta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated; // <-- ¡Importante añadir!
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    
    @Column(nullable = false, precision = 10, scale = 2) // Basado en tu diagrama
    private BigDecimal saldo;

    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", 
                referencedColumnName = "idCliente", 
                nullable = false, 
                unique = true) 
    private Cliente cliente;
}
