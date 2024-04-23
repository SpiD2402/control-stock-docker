package com.example.producto.util.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProducto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
    private Long id_categoria;

    @NotNull
    private Long id_proveedor;
    @Min(0)
    @NotNull
    private double precio_compra;
    @Min(0)
    @NotNull
    private double precio_venta;

    @NotNull
    private  int stock;

}
