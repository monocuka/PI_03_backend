package com.backend.integrador.controllers;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integrador.dto.error.ErrorResponseDTO;
import com.backend.integrador.service.IImagenProductoService;

@RestController
@CrossOrigin
@RequestMapping("/api/imagenes")
public class ImagenesController {

    @Autowired
    private IImagenProductoService imagenService;

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerImagen(@PathVariable String nombre) {
        try {
            // Obtener el FileInputStream de la imagen desde el servicio
            FileInputStream inputStream = imagenService.obtenerImagen(nombre);

            // Crear un InputStreamResource para la imagen
            InputStreamResource resource = new InputStreamResource(inputStream);

            // Devolver la imagen como respuesta
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG) // O el tipo de contenido correcto de la imagen
                    .body(resource);

        } catch (IOException e) {
            // Si la imagen no se encuentra, devolver un 404 Not Found junto con un JSON
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("La imagen no se encontró");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }
    
}
