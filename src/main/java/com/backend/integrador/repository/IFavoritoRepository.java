package com.backend.integrador.repository;

import com.backend.integrador.entity.Favorito;
import com.backend.integrador.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoritoRepository extends JpaRepository<Favorito, Long> {

    @Query("SELECT p FROM Favorito f JOIN f.producto p WHERE f.usuario.id = :userId")
    List<Producto> findProductosFavoritosPorUsuario(@Param("userId") Long userId);

}
