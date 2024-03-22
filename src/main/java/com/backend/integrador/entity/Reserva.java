package com.backend.integrador.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table( name = "reserva") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "res_id")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/mm/dd")  
    @Column( name = "res_fecha_desde")
    private LocalDate fecha_desde;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/mm/dd")  
    @Column( name = "res_fecha_hasta")
    private LocalDate fecha_hasta;

    @Column( name = "resd_cantidad" )
    private int resd_cantidad;
    @ManyToOne
    @JoinColumn(name = "resd_idproducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "res_usuario", nullable = false)
    private Usuario usuario;

    // @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval=true)
    // private Set<ReservaDetalle> reservaDetalles;
}