package com.backend.integrador.controllers;

import com.backend.integrador.Entity.Producto;
import com.backend.integrador.service.ImagenProductoService;
import com.backend.integrador.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ImagenProductoService imagenProductoService;

    @GetMapping
    public List<Producto> findAll(){
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestPart("producto") String pString,
                                           @RequestPart("imagen") MultipartFile imagen) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Producto p = mapper.readValue(pString, Producto.class);
            Producto newP = productService.createProduct(p);
            imagenProductoService.guardarImagenProducto(p, imagen);
            return ResponseEntity.ok(newP);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            while(cause != null) {
                if (cause instanceof SQLIntegrityConstraintViolationException) {
                    SQLIntegrityConstraintViolationException sqlException = (SQLIntegrityConstraintViolationException) cause;
                    System.out.println(sqlException.getMessage()); // print the exception message to the console
                    if (sqlException.getMessage().contains("nombre_UNIQUE")) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un producto con ese nombre");
                    } else if (sqlException.getMessage().contains("codigo_UNIQUE")) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un producto con ese codigo");
                    }
                }
                cause = cause.getCause();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing product data.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        Optional<Producto> product = productService.findById(id);
        if(product.isPresent()){
            Producto producto = product.get();
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Producto> findByName(@PathVariable String name){
        Optional<Producto> product = productService.findByName(name);
        if(product.isPresent()){
            Producto producto = product.get();
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        Optional<Producto> product = productService.findById(id);
        if (product.isPresent()){
           productService.deleteProduct(id);
           return ResponseEntity.ok("Producto " + product.get().getNombre() + " fue eliminado.");
        }
        return ResponseEntity.badRequest().body("El producto que se quiere eliminar no existe.");
    }

}
