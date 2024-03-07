package com.backend.integrador.controllers;

import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/")
    public List<CategoriaSalidaDTO> obtenerCategorias(){
        List<CategoriaSalidaDTO> listaCategorias = categoriaService.obtenerTodosLosCategorias();
        return listaCategorias;
    }
}
