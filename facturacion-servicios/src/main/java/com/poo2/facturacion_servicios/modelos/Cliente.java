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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
// Se quita @AllArgsConstructor para usar el constructor personalizado
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    
    @Column(nullable = false, length = 200)
    private String razonSocial;

    
    @Column(nullable = false, unique = true, length = 11)
    private String cuit;


    @Column(nullable = false, length = 250)
    private String domicilio;

    // Se agrega el campo 'email' que venía en tu rama (HU-01)
    @Column(nullable = false)
    private String email;

    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CondicionIVA condicionIVA;

    

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cuenta cuenta;

    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Factura> facturas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NotaDeCredito> notasDeCredito;

    
    public Cliente(String razonSocial, String cuit, String domicilio, String email, CondicionIVA condicionIVA) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.email = email;
        this.condicionIVA = condicionIVA;
    }
}