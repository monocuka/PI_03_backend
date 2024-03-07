package com.backend.integrador.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntradaDTO {
    private Long id; // Para mi esto no deberia de ir en el caso de que el admin pueda crear nuevas categorias.
    private String nombre;
    private String descripcion;
}

/*
* Lo que recibimos del front
* */