package com.backend.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integrador.entity.Caracteristica;

public interface ICaracteristicasRepository extends JpaRepository<Caracteristica, Long>{
    
}
