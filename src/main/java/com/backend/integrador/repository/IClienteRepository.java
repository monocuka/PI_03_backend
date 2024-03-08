package com.backend.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.integrador.entity.Usuario;

@Repository
public interface IClienteRepository extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String email);
}