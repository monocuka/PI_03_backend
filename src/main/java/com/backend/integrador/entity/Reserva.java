package com.backend.integrador.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table( name = "reservas") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "res_id")
    private Long id;
    
    @Column( name = "res_fecha_desde")
    private LocalDate fecha_desde;
      
    @Column( name = "res_fecha_hasta")
    private LocalDate fecha_hasta;

    @Column( name = "res_cantidad" )
    private int cantidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pro_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usu_id", nullable = false)
    private Usuario usuario;

}