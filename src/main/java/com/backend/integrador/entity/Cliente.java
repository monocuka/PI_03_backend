package com.backend.integrador.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "cli_id")
    private Long id;
    @Column( name = "cli_nombre")
    private String nombre;
    @Column( name = "cli_apellido")
    private String apellido;
    @Column( name = "cli_email", unique =  true, nullable = false)
    private String email;
    @Column( name = "cli_contrasena")
    private String contrasena;
    @Column( name = "cli_telefono")
    private String telefono;
    @Column( name = "cli_direccion")
    private String direccion;
    @Column( name = "cli_ciudad")
    private String ciudad;
    @Column( name = "cli_cedula")
    private int cedula;

    // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval=true)
    // private Set<Reserva> reservas;
    
}
