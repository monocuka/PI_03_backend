package com.backend.integrador.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "pro_id")
    private  Long id;
    @Column( name = "pro_nombre")
    private String nombre;
    @Column( name = "pro_descripcion")
    private String descripcion;
    @Column( name = "pro_precio")
    private Double precio;
    
    @ManyToOne
    @JoinColumn(name = "pro_idcategoria", nullable = false)
    private Categoria categoria;   
}
