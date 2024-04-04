package com.backend.integrador.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.integrador.entity.Producto;
import com.backend.integrador.entity.Reserva;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT p FROM Reserva r RIGHT JOIN r.producto p WHERE " +
            "(r.id IS NULL OR " +
            "( (:fechaInicio NOT BETWEEN r.fecha_desde AND r.fecha_hasta) AND " +
            "(:fechaFin NOT BETWEEN r.fecha_desde AND r.fecha_hasta) ) AND " +
            "NOT (:fechaInicio < r.fecha_desde AND :fechaFin > r.fecha_hasta))")
    List<Producto> filtrarProductoPorRangoFechas(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT p FROM Reserva r RIGHT JOIN r.producto p WHERE " +
           "(r.id IS NULL OR " +
           "( (:fechaInicio NOT BETWEEN r.fecha_desde AND r.fecha_hasta) AND " +
           "(:fechaFin NOT BETWEEN r.fecha_desde AND r.fecha_hasta) ) AND " +
           "NOT (:fechaInicio < r.fecha_desde AND :fechaFin > r.fecha_hasta)) AND " +
           "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Producto> filtrarProductosPorRangoFechasYNombre(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin, @Param("busqueda") String busqueda);

    List<Reserva> findByProductoId(Long productoId);

    @Query("SELECT r FROM Reserva r WHERE r.usuario.email = :email")
    List<Reserva> searchByUsuarioEmail(@Param("email") String email);
    
}




 /*
    #query para traer prod reservados
    SELECT * FROM reservas AS R
    INNER JOIN productos AS P
    ON R.pro_id = P.pro_id;

    // fecha de inicio y fecha de fin son los unicos params que necesito
    SELECT * FROM reservas AS R
    RIGHT JOIN productos AS P
    ON R.pro_id = P.pro_id
    WHERE (
	(R.res_id IS NULL) OR (
            (	"2024-03-27" NOT BETWEEN R.res_fecha_desde AND R.res_fecha_hasta)
    AND
            ("2024-03-31" NOT BETWEEN R.res_fecha_desde AND R.res_fecha_hasta)
        )
    AND NOT("2024-03-27" < R.res_fecha_desde AND "2024-03-31" > R.res_fecha_hasta)
    )
        ##
        (03-22,03-26) fecha reserva
            (03-18,03-21)  -> lo trae - ok
            (03-18,03-22)  -> no lo trae - ok
            (03-18,03-31)  -> no lo trae - ok
            (03-23,03-25)  -> no lo trae - ok
            (03-26,03-31)  -> no lo trae - ok
            (03-27,03-31)  -> lo trae -ok
    */