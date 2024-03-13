package com.backend.integrador.dto.producto;

import java.io.Serializable;
import java.util.List;

import com.backend.integrador.dto.caracteristicas.CaracteristicaEntradaDTO;
import com.backend.integrador.dto.categoria.CategoriaEntradaDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO implements Serializable{
    private static final  long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private CategoriaEntradaDTO categoria; 
    private List<CaracteristicaEntradaDTO> caracteristicas;
}

