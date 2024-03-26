package com.backend.integrador.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSalidaBusquedaSimilar {
    private Long id;
    private String Nombre;
}
