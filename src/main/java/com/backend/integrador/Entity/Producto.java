package com.backend.integrador.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pro_id;
    @Column(name = "pro_nombre")
    private String nombre;
    @Column(name = "pro_codigo")
    private String codigo;
    @Column(name = "pro_descripcion")
    private String descripcion;
   // @Column(name = )
   // private String[] imgs;
    @Column(name = "pro_precio")
    private Double precio;
    @Column(name = "pro_cantidad")
    private int cantidad;
    @ManyToOne
    @JoinColumn(name = "pro_idcategoria")
    private Categoria categoria;
}
