package com.backend.integrador.service;

import java.io.FileInputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface ICategoriaService {
    public CategoriaSalidaDTO guardaCategoria(String categorisaStr, MultipartFile imagen) throws JsonProcessingException;
    public CategoriaSalidaDTO obtenerCategoriaPorId(Long id);
    public List<CategoriaSalidaDTO> obtenerTodasLasCategorias();
    public void eliminarCategoria(Long idCategoria);
    public FileInputStream obtenerImagen(String nombre) throws IOException;
}

