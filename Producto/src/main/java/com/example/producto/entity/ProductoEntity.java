package com.example.producto.entity;


import com.example.producto.util.Audit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class ProductoEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nombre;

    private String descripcion;

    private Long id_categoria;

    private Long id_proveedor;

    private double precio_compra;

    private double precio_venta;

    private  int stock;

    private int estado;



}
