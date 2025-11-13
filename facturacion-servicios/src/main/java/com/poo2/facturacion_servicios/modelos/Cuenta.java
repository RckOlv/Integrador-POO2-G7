package com.poo2.facturacion_servicios.modelos;

import jakarta.persistence.*;
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

    // Relación con Cliente (uno a uno)
    @OneToOne(mappedBy = "cuenta")
    private Cliente cliente;
}
