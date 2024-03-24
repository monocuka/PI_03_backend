package com.backend.integrador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.dto.error.ErrorResponseDTO;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.service.ICategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/listar")
    public List<CategoriaSalidaDTO> obtenerCategorias(){
        List<CategoriaSalidaDTO> listaCategorias = categoriaService.obtenerTodasLasCategorias();
        return listaCategorias;
    }

    @PostMapping(value = "/guardar", consumes = { "multipart/form-data" })
    public ResponseEntity<?> guardarCategoria(@RequestParam("categoria") String categoriaStr, 
                                                @RequestParam("imagen") MultipartFile imagen){
        
        try {
            CategoriaSalidaDTO categoriaSalidaDTO = categoriaService.guardaCategoria(categoriaStr, imagen);
            return new ResponseEntity<>(categoriaSalidaDTO, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Error de Estructura de json");
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(errorResponse);
        }

    }
}
