package com.poo2.facturacion_servicios.modelos;

import jakarta.persistence.*; // Importante: usa jakarta.persistence, no javax.persistence para Spring Boot 3+
import java.math.BigDecimal;
import lombok.Data; // Para getters, setters, etc.

@Entity // Le dice a Spring que esta clase es una tabla de BD
@Table(name = "servicio") // Define el nombre de la tabla
@Data // Lombok: genera getters, setters, toString, etc. automáticamente
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @Column(nullable = false, length = 100)
    private String nombre;

    // RNF-03: Usar BigDecimal para precisión financiera
    @Column(nullable = false, precision = 10, scale = 2) 
    private BigDecimal montoBase;

    // Ej: 21.00, 10.50
    @Column(nullable = false, precision = 4, scale = 2) 
    private BigDecimal alicuotaIVA; 
}