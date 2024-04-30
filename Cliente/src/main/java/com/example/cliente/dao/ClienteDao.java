package com.example.cliente.dao;

import com.example.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao  extends JpaRepository<ClienteEntity,Long> {
}
