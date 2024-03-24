package com.backend.integrador.dto.categoria.mapper;

import com.backend.integrador.dto.categoria.CategoriaEntradaDTO;
import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.entity.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoriaMapper {
    
    private static  ObjectMapper objectMapper = new ObjectMapper();

    public static CategoriaSalidaDTO toSalidaDTO(Categoria categoria){
        return  objectMapper.convertValue(categoria, CategoriaSalidaDTO.class);
    }
    
    public static Categoria toCategoria(CategoriaEntradaDTO categoriaEntradaDTO){
        return   objectMapper.convertValue(categoriaEntradaDTO, Categoria.class);
    }

    public static CategoriaSalidaDTO toCategoriaSalidaDTO(Categoria categoria, String imagen ){
        CategoriaSalidaDTO categoriaSalidaDTO = new CategoriaSalidaDTO();

        categoriaSalidaDTO.setId(categoria.getId());
        categoriaSalidaDTO.setNombre(categoria.getNombre());
        categoriaSalidaDTO.setDescripcion(categoria.getDescripcion());
        categoriaSalidaDTO.setUrlImagen(imagen);

        return categoriaSalidaDTO;
    }
}
