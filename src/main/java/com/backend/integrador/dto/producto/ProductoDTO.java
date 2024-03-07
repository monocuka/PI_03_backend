package com.backend.integrador.dto.producto;

import java.io.Serializable;


import com.backend.integrador.entity.Categoria;
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
    private  Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Categoria categoria;
}

