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
    @ManyToOne
    @JoinColumn(name = "ima_idproducto")
    private Producto producto;
    @Column(name = "ima_link")
    private String url;
    @Column(name = "ima_descripcion")
    private String descripcion;

    public ImagenProducto(Producto p, String url, String descripcion){
        this.producto = p;
        this.url = url;
        this.descripcion = descripcion;
    }
}
