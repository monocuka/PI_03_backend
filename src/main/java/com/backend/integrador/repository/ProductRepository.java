package com.backend.integrador.repository;

import com.backend.integrador.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> deleteByNombre(String nombre);

}
