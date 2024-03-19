package com.backend.integrador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.service.ICategoriaService;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    public List<CategoriaSalidaDTO> obtenerCategorias(){
        List<CategoriaSalidaDTO> listaCategorias = categoriaService.obtenerTodasLasCategorias();
        return listaCategorias;
    }
}
