package com.poo2.facturacion_servicios.controladores.dto;

import com.poo2.facturacion_servicios.enums.CondicionIVA;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearClienteRequest {

    @NotEmpty(message = "La razón social no puede estar vacía")
    private String razonSocial;

    @NotEmpty(message = "El CUIT no puede estar vacío")
    @Size(min = 11, max = 11, message = "El CUIT debe tener 11 dígitos")
    private String cuit;

    @NotEmpty(message = "El domicilio no puede estar vacío")
    private String domicilio;

    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotNull(message = "La condición de IVA no puede ser nula")
    private CondicionIVA condicionIVA;
    
}
