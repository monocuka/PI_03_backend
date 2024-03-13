package com.backend.integrador.dto.producto;

import java.util.ArrayList;
import java.util.List;

import com.backend.integrador.dto.caracteristicas.CaracteristicaDTO;
import com.backend.integrador.dto.categoria.CategoriaDTO;
import com.backend.integrador.dto.imagen.ImagenSalidaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSalidaDTO{
    private Long id;
    private String nombre;
    private String descripcion;
    private  Double precio;
    private List<ImagenSalidaDTO> imagenes = new ArrayList<>();
    private CategoriaDTO categoria;
    private List<CaracteristicaDTO> caracteristicas;
}
