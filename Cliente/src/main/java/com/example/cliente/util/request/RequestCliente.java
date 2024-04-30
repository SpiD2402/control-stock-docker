package com.example.cliente.util.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCliente {

    @NotBlank
    private String nombre;

    @NotBlank
    private String direccion;

    @Pattern(regexp="^9\\d{8}$", message="El número de teléfono debe comenzar con 9 y tener 9 dígitos en total")
    private String telefono;

    @NotBlank
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;
}
