package com.backend.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integrador.entity.ReservaDetalle;

public interface IReservaDetalleRepository extends JpaRepository<ReservaDetalle, Long> {
    
}
