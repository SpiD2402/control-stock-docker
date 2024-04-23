package com.example.producto.entity.model;

import com.example.producto.util.Audit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Proveedor extends Audit {

    private Long id_proveedor;

    private String nombre;

    private String direccion;

    private String telefono;

    private String email;

    private int estado ;


}
