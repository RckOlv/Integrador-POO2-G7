package com.poo2.facturacion_servicios.modelos;

import java.util.List;

import com.poo2.facturacion_servicios.enums.EstadoCuenta;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    // Relación con Cliente (uno a uno)
    @OneToOne(mappedBy = "cuenta")
    private Cliente cliente;

    // Relación con ServicioContratado (uno a muchos)
    @OneToMany(mappedBy = "cuenta")
    private List<ServicioContratado> serviciosContratados;
}
