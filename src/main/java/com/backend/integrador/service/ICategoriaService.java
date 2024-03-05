package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.entity.Categoria;

public interface ICategoriaService {
    public Categoria guardaCategoria(Categoria categoria);
    public Categoria obtenerCategoriaPorId(Long id);
    public List<Categoria> obtenerTodosLosCategorias();
    public void eliminarCategoria(Long idCategoria);
}

