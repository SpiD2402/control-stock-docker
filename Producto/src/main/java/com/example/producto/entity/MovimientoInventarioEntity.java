package com.example.producto.entity;

import com.example.producto.util.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movimiento_inventario")
public class MovimientoInventarioEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movimiento;

    private Long id_producto;

    private String tipo_movimiento;

    private int cantidad;

    private Timestamp fecha_movimiento;


}
