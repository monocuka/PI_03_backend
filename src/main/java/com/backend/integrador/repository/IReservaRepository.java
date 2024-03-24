package com.backend.integrador.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.integrador.entity.Producto;
import com.backend.integrador.entity.Reserva;
import java.util.Optional;


public interface IReservaRepository extends JpaRepository<Reserva, Long> {
    
    @Query("SELECT p FROM Producto p WHERE p.id = :productoId AND NOT EXISTS (SELECT r FROM Reserva r WHERE r.producto.id = p.id AND (r.fechaDesde <= :fechaFin AND r.fechaHasta >= :fechaInicio))")
    Optional<Producto> findProductosByReservaAndFecha(@Param("productoId") Long productoId, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
}
