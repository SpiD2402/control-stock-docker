package com.example.proveedor.dao;

import com.example.proveedor.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorDao extends JpaRepository<ProveedorEntity,Long> {
}
