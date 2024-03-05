package com.backend.integrador.entity;

import jakarta.persistence.CascadeType;
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
@Table( name = "imagen_procducto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenProducto {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "ima_id")
    private Long id;
    @Column( name = "ima_link")
    private  String urlImagen;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ima_idproducto", nullable = false)
    private Producto producto;
}
