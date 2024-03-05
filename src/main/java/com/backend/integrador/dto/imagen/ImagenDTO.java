package com.backend.integrador.dto.imagen;

import com.backend.integrador.dto.producto.ProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenDTO {
    private Long id;
    private  String urlImagen;
    private ProductoDTO productoDTO;
}