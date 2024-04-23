package com.example.categoria.util.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCategoria  {

    @NotBlank
    private String nombre;

    private String descripcion;

}
