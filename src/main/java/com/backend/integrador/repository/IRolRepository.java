package com.backend.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integrador.entity.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByNombreRol(String nombre);
    
}
