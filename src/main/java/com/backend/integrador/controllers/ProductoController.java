package com.backend.integrador.controllers;

import java.util.List;

import com.backend.integrador.dto.producto.ProductoDTO;
import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.error.ErrorResponseDTO;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.service.IProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@CrossOrigin
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @PostMapping(value = "/guardar", consumes = { "multipart/form-data" })
    public ResponseEntity<?> guardarProducto(@RequestParam("producto") String productoStr, 
                                                @RequestParam("imagen") MultipartFile imagen) {
        try {
            ProductoSalidaDTO productoSalidaDTO = productoService.guardarProducto(productoStr, imagen);
            System.out.println(imagen);
            return new ResponseEntity<>(productoSalidaDTO, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            // Si la imagen no se encuentra, devolver un 404 Not Found junto con un JSON
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Error al subir la imagen");
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(errorResponse);
        }
    }
    @PostMapping(value = "/actualizar", consumes = { "multipart/form-data" })
    public ResponseEntity<?> actualizarProducto(@RequestParam("producto") String productoStr,
                                                @RequestParam("imagen") MultipartFile imagen){
        try {
            ProductoSalidaDTO productoSalidaDTO = productoService.modificarProducto(productoStr, imagen);
            System.out.println(imagen);
            return new ResponseEntity<>(productoSalidaDTO, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            // Si la imagen no se encuentra, devolver un 404 Not Found junto con un JSON
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Error al subir la imagen");
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(errorResponse);
        }
    }

    @GetMapping("/")
    public List<ProductoSalidaDTO> obtenerProductoConImagenes(){
        List<ProductoSalidaDTO> listaProductos = productoService.obtenerTodosLosProductos();
        return listaProductos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        System.out.println("Getmapping by id");
        ProductoSalidaDTO productoSalidaDTO = productoService.obtenerProductoPorId(id);
        if (productoSalidaDTO != null) {
            return ResponseEntity.ok().body(productoSalidaDTO);
        } else {
            // Si la imagen no se encuentra, devolver un 404 Not Found junto con un JSON
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Id de Producto no encontrado");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @GetMapping("/buscarNombre/{nombre}")
    public ProductoSalidaDTO buscarPorProducto(@PathVariable String nombre){
        return productoService.buscarPorNombre(nombre);
    }

    @PostMapping("/prueba")
    public String prueba() {
        return "Todo funcioanndo";
    }
    
    
}

