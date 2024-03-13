package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.dto.categoria.CategoriaEntradaDTO;
import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;

public interface ICategoriaService {
    public CategoriaSalidaDTO guardaCategoria(CategoriaEntradaDTO categoria);
    public CategoriaSalidaDTO obtenerCategoriaPorId(Long id);
    public List<CategoriaSalidaDTO> obtenerTodasLasCategorias();
    public void eliminarCategoria(Long idCategoria);
}

