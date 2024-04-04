package com.backend.integrador.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.dto.error.ErrorResponseDTO;
import com.backend.integrador.service.ICategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/listar")
    public List<CategoriaSalidaDTO> obtenerCategorias(){
        List<CategoriaSalidaDTO> categorias = categoriaService.obtenerTodasLasCategorias();
        return categorias;
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

    @PostMapping(value  = "/actualizar", consumes = {"multipart/form-data"})
    public ResponseEntity<?> actualizarProducto(@RequestParam("categoria") String productoStr,
                                                @RequestParam(value = "imagen", required = false) MultipartFile imagen){
        try {
            CategoriaSalidaDTO categoriaSalidaDTO = categoriaService.modificarCategoria(productoStr, imagen);
            return new ResponseEntity<>(categoriaSalidaDTO, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    } 

    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<?> obtenerImagenCategoria(@PathVariable String nombre){
        try{
            FileInputStream inputStream = categoriaService.obtenerImagen(nombre);

            InputStreamResource resource = new InputStreamResource(inputStream);

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG) // O el tipo de contenido correcto de la imagen
                    .body(resource);
        }catch (IOException e) {
            // Si la imagen no se encuentra, devolver un 404 Not Found junto con un JSON
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("La imagen no se encontró");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }
}
