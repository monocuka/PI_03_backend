package com.backend.integrador.controllers;

import com.backend.integrador.Entity.Producto;
import com.backend.integrador.service.ImagenProductoService;
import com.backend.integrador.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<Producto> createProduct(@RequestPart("producto") String pString,
                                                  @RequestPart("imagen") MultipartFile imagen) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Producto p = mapper.readValue(pString, Producto.class);
        Producto newP = productService.createProduct(p);
        imagenProductoService.guardarImagenProducto(p, imagen);
        return ResponseEntity.ok(newP);
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
    @GetMapping("/{name}")
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
