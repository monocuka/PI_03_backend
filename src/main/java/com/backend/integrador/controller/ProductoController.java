package com.backend.integrador.controller;

import com.backend.integrador.Entity.Producto;
import com.backend.integrador.service.ProductoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping("crear")
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto p){
        return ResponseEntity.ok(productoService.guardarProducto(p));
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id){
        Optional<Producto> productoOptional = productoService.buscarPorId(id);
        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("buscar/{nombre}")
    public ResponseEntity<Producto> buscarPorNombre(@PathVariable String nombre){
        Optional<Producto> productoOptional = productoService.buscarPorNombre(nombre);
        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        Optional<Producto> productoABorrar = productoService.buscarPorId(id);
        if (productoABorrar.isPresent()){
           productoService.eliminarProducto(id);
           return ResponseEntity.ok("Producto " + productoABorrar.get().getNombre() + " fue eliminado.");
        }
        return ResponseEntity.badRequest().body("El producto que se quiere eliminar no existe.");
    }

    @GetMapping("/all")
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }
}
