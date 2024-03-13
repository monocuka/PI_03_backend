package com.backend.integrador.dto.producto;

import java.util.List;

import com.backend.integrador.dto.caracteristicas.CaracteristicaEntradaDTO;
import com.backend.integrador.dto.categoria.CategoriaEntradaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductoEntradaDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private CategoriaEntradaDTO categoria; 
    private List<CaracteristicaEntradaDTO> caracteristicas;
}
