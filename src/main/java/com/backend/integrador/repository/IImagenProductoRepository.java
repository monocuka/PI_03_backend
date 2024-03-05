package com.backend.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.integrador.entity.ImagenProducto;

@Repository
public interface IImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {
    List<ImagenProducto> findByProductoId(Long productId);
}
