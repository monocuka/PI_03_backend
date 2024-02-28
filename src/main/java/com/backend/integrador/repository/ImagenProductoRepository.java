package com.backend.integrador.repository;

import com.backend.integrador.Entity.ImagenProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {

    Optional<ImagenProducto> deleteById(int id);
    Optional<ImagenProducto> findById(int id);
}
