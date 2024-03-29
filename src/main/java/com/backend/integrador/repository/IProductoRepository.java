package com.backend.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.integrador.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
    @Query(value = "SELECT * FROM productos ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<Producto> obtenerProductosAleatorios();

    List<Producto> findFirst4ByNombreContainingIgnoreCase(String query);

    List<Producto> findByCategoriaId(Long categoriaId);
    
}