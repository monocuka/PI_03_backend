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

    // @Query("SELECT p FROM Productos p WHERE p.nombre LIKE %:busqueda% AND NOT EXISTS (SELECT 1 FROM Reserva r WHERE r.producto.id = p.id AND (r.fecha_desde <= :desde AND r.fecha_hasta >= :hasta))")
    // List<Producto> buscarProductosFechas(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta, @Param("busqueda") String busqueda);
}