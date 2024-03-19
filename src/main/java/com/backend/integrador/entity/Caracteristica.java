package com.backend.integrador.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "caracteristicas" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Caracteristica {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "cat_id" )
    private Long id;

    @Column( name="cat_nombre", nullable=false)
    private String nombre;

    @Column( name = "cat_icono" )
    private String icono;
}
