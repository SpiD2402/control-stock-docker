package com.example.producto.dao;

import com.example.producto.entity.MovimientoInventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoInventarioDao extends JpaRepository<MovimientoInventarioEntity,Long> {
}
