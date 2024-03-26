package com.backend.integrador.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntradaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
