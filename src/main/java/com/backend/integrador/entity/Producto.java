package com.backend.integrador.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "productos")
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
    
    @Column( name = "pro_descripcion", length = 500)
    private String descripcion;
    
    @Column( name = "pro_precio")
    private Double precio;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pro_idcategoria", nullable = false)
    private Categoria categoria; 
    
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    // @Column( name = "caracteristicas", nullable =  true)
    private Set<Caracteristica> caracteristicas;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    private Set<Reserva> reservas;

    public Producto(Long id, String nombre, String descripcion, Double precio, Categoria categoria, Set<Caracteristica> caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.reservas = new HashSet<>();
    }

}
