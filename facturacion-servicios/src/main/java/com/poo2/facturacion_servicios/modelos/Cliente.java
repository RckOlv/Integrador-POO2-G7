package com.poo2.facturacion_servicios.modelos;
import java.util.Set;

import com.poo2.facturacion_servicios.enums.CondicionIVA;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente; // 

    @Column(nullable = false, length = 200)
    private String razonSocial; 

    @Column(nullable = false, unique = true, length = 20)
    private String cuit;

    @Column(length = 250)
    private String domicilio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CondicionIVA condicionIVA; 

    // Relación 1-a-1 con Cuenta
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cuenta cuenta;
    
    // Relación 1-a-N con Factura
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Factura> facturas;

    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NotaDeCredito> notasDeCredito;
  
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