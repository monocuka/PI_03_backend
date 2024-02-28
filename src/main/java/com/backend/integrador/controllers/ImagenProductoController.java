package com.backend.integrador.controllers;

import com.backend.integrador.Entity.ImagenProducto;
import com.backend.integrador.Entity.Producto;
import com.backend.integrador.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/imagenes")
public class ImagenProductoController {
    @Autowired
    private ImagenProductoService imagenProductoService;

    @PostMapping("crear")
    public ResponseEntity<ImagenProducto> registrarImagen(@RequestBody ImagenProducto i){
        return ResponseEntity.ok(imagenProductoService.guardarImagenProducto(i));
    }
    @GetMapping("buscar/{id}")
    public ResponseEntity<ImagenProducto> buscarPorId(@PathVariable int id){
        Optional<ImagenProducto> ImagenProductoOptional = imagenProductoService.buscarPorid(id);
        if(ImagenProductoOptional.isPresent()){
            ImagenProducto imagenProducto = ImagenProductoOptional.get();
            return ResponseEntity.ok(imagenProducto);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id){
        Optional<ImagenProducto> imagenProducto = imagenProductoService.buscarPorid(id);
        if (imagenProducto.isPresent()){
            imagenProductoService.EliminarImagenProducto(id);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().body("El producto que se quiere eliminar no existe.");
    }
    @GetMapping("/all")
    public List<ImagenProducto> listarProductos(){
        return imagenProductoService.listarImagenes();
    }
}
