package com.backend.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integrador.entity.Reserva;

public interface IReservaRepository extends JpaRepository<Reserva, Long> {
    
}
