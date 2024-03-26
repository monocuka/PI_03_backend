package com.backend.integrador.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.error.ErrorResponseDTO;
import com.backend.integrador.dto.producto.ProductoSalidaBusquedaSimilar;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.service.IProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private IProductoService productoService;


    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> buscarPorCategoria(@PathVariable Long id){
        List<ProductoSalidaDTO> productosCategoria = productoService.buscarPorCategoria(id);
        if(productosCategoria == null || productosCategoria.isEmpty()){
            return new ResponseEntity<>("No hay productos con esa categoria.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(productosCategoria);
    }

    @GetMapping("/disponibilidad/fechainicial/{fechaInicial}/fechafinal/{fechaFinal}")
    public ResponseEntity<?> buscarProductoXFechas(
        @RequestParam(required = false) String busqueda,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate fechaInicial,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate fechaFinal) {

        List<ProductoSalidaDTO> productosDisponibles = null;
        
        if(busqueda == null){
            productosDisponibles = productoService.productosDisponiblesFechas(fechaFinal, fechaFinal);
        } else {
            productosDisponibles = productoService.productosDisponiblesFechasYNombre(fechaInicial, fechaFinal, busqueda);
        }

        if (productosDisponibles == null || productosDisponibles.isEmpty()) {
            return new ResponseEntity<>("No hay productos buscados.", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(productosDisponibles);
        }
    }

    @GetMapping("/nombresSimilares/{busqueda}")
    public List<ProductoSalidaBusquedaSimilar> buscarProductos(@PathVariable String busqueda){
        return productoService.buscarProductosSimilares(busqueda);
    }

    @PostMapping(value = "/guardar", consumes = { "multipart/form-data" })
    public ResponseEntity<?> guardarProducto(@RequestParam("producto") String productoStr, 
                                                @RequestParam("imagen") MultipartFile imagen) {
        try {
            ProductoSalidaDTO productoSalidaDTO = productoService.guardarProducto(productoStr, imagen);
            return new ResponseEntity<>(productoSalidaDTO, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Error de Estructura de json");
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(errorResponse);
        }
        
    }

    @PostMapping(value = "/actualizar", consumes = { "multipart/form-data" })
    public ResponseEntity<?> actualizarProducto(@RequestParam("producto") String productoStr,
                                                @RequestParam(value = "imagen", required = false) MultipartFile imagen){
        try {
            ProductoSalidaDTO productoSalidaDTO = productoService.modificarProducto(productoStr, imagen);
            return new ResponseEntity<>(productoSalidaDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @GetMapping("/listar")
    public List<ProductoSalidaDTO> obtenerProductoConImagenes(){
        List<ProductoSalidaDTO> listaProductos = productoService.obtenerTodosLosProductos();
        return listaProductos;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        ProductoSalidaDTO productoSalidaDTO = productoService.obtenerProductoPorId(id);
        if (productoSalidaDTO != null) {
            return ResponseEntity.ok().body(productoSalidaDTO);
        } else {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Id de Producto no encontrado");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }
    
    @GetMapping("/productosRecomendados")
    public ResponseEntity<List<ProductoSalidaDTO>> obtenerProductosRecomendados(){
        return ResponseEntity.ok().body(productoService.obtenerProductosAleatorios());
    }

    @GetMapping("/prueba")
    public String prueba() {
        return "Todo funcioanndo";
    }
    
    
}

