package com.backend.integrador.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favoritos")
@NoArgsConstructor
@AllArgsConstructor

public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "usu_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "pro_id")
    private Producto producto;

    public Favorito(Usuario usuario, Producto producto) {
        this.usuario = usuario;
        this.producto = producto;
    }
}
