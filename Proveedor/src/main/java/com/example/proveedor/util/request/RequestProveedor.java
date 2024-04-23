package com.example.proveedor.util.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProveedor {
    @NotBlank
    private String nombre;

    @NotBlank
    private String direccion;

    @NotBlank
    @Size(min = 9, max = 9, message = " debe tener 9 dígitos")
    private String telefono;

    @Email
    @NotBlank
    private String email;


}
