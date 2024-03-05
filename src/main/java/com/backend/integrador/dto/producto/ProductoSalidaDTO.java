package com.backend.integrador.dto.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.backend.integrador.dto.categoria.CategoriaDTO;
import com.backend.integrador.dto.imagen.ImagenSalidaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSalidaDTO  implements Serializable{
    private static  final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String descripcion;
    private  Double precio;
    private List<ImagenSalidaDTO> imagenes = new ArrayList<>();
    private CategoriaDTO categoria;
}
