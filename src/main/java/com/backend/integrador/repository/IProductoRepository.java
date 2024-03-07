package com.backend.integrador.repository;

import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.integrador.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
    public Producto findByNombre(String nombre);
}