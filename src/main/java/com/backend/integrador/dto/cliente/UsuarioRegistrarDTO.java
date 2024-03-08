package com.backend.integrador.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegistrarDTO {
    private String nombre;
    private String apellido;
    private String contrasena;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    private int cedula;
}


