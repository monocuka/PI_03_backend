package com.backend.integrador.repository;

import com.backend.integrador.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByName(String nombre);
    Optional<Producto> deleteByName(String nombre);

}
