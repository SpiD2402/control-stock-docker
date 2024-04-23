package com.example.producto.entity.model;

import com.example.producto.util.Audit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Categoria  extends Audit {

    private Long id_categoria;


    private String nombre;

    private String descripcion;


    private int estado;

}
