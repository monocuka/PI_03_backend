package com.backend.integrador.dto.categoria.mapper;

import com.backend.integrador.dto.categoria.CategoriaDTO;
import com.backend.integrador.dto.categoria.CategoriaEntradaDTO;
import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.entity.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoriaMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Categoria toCategoria(CategoriaEntradaDTO categoriaEntradaDTO){ // Convierte CategoriaEntradaDTO -> Categoria
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaEntradaDTO.getNombre());
        categoria.setDescripcion(categoriaEntradaDTO.getDescripcion());
        return categoria;
    }

    public static CategoriaSalidaDTO toCategoriaSalidaDTO(Categoria categoria){ // Convierte Categoria -> CategoriaSalidaDTO
        return objectMapper.convertValue(categoria, CategoriaSalidaDTO.class);
    }
}
