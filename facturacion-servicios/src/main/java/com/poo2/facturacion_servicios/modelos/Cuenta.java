package com.poo2.facturacion_servicios.modelos;

import java.math.BigDecimal; // <-- AÑADIDO
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poo2.facturacion_servicios.enums.EstadoCuenta;

import jakarta.persistence.Column; // <-- AÑADIDO
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    // RNF-03: Requerido por el diagrama y por la HU-07
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO; // <-- AÑADIDO

    // CORREGIDO: Esta es ahora la "dueña" de la relación con Cliente
    // (Porque Cliente.java ahora usa "mappedBy")
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "idCliente") // <-- AÑADIDO
    private Cliente cliente;

    // Relación con ServicioContratado (uno a muchos)
    @OneToMany(mappedBy = "cuenta")
    @JsonIgnore
    private List<ServicioContratado> serviciosContratados;
    
    // (Relación con Factura se movió a Cliente en la HU-07)
}