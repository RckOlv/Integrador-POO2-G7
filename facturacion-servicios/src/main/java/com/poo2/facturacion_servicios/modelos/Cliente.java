package com.poo2.facturacion_servicios.modelos;

import com.poo2.facturacion_servicios.enums.CondicionIVA; 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente; // <-- Actualizado (según diagrama)

    @Column(nullable = false, length = 100)
    private String razonSocial; // <-- Actualizado (en vez de 'nombre')

    @Column(nullable = false, unique = true, length = 11)
    private String cuit; // <-- Añadido (según HU-01)

    @Column(nullable = false)
    private String domicilio; // <-- Añadido (según HU-01)

    @Column(nullable = false)
    private String email; // <-- (Ya estaba, lo dejamos)

    @Enumerated(EnumType.STRING) // Guardar como "MONOTRIBUTISTA", etc.
    @Column(nullable = false)
    private CondicionIVA condicionIVA; // <-- Añadido (CRÍTICO para facturar)

    // Relación 1 a 1 con Cuenta (HU-02)
    // CascadeType.ALL: Cuando se guarda/elimina un Cliente,
    // también se guarda/elimina la Cuenta asociada.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private Cuenta cuenta;

    // Constructor para la lógica de creación (T-01.3)
    public Cliente(String razonSocial, String cuit, String domicilio, String email, CondicionIVA condicionIVA) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.email = email;
        this.condicionIVA = condicionIVA;
    }
}