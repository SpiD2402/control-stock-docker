package com.example.producto.util.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMovimientoInventario {

    @NotNull
    private Long id_producto;

    @NotBlank
    @Pattern(regexp = "^(entrada|salida)$", message = "El tipo de movimiento debe ser 'entrada' o 'salida'")
    private String tipo_movimiento;

    @Min(1)
    @NotNull
    private int cantidad;

}
