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
@Table(name = "imagen_producto")
public class ImagenProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ima_id")
    private int id;
    @Column(name = "ima_idproducto")
    private int idProducto;
    @Column(name = "ima_link")
    private String url;
    @Column(name = "ima_descripcion")
    private String descripcion;
}
