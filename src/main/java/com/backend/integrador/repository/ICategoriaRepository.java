package com.backend.integrador.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.integrador.entity.Categoria;


@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long>{

    
    
}