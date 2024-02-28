package com.backend.integrador.service;

import com.backend.integrador.Entity.Producto;
import com.backend.integrador.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Producto createProduct(Producto p){
        return productRepository.save(p);
    }

//    public void actualizarProducto(Producto p){
//        return productoRepository.save(p);
//    }

    @Transactional
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Producto> findAll(){
        return productRepository.findAll();
    }

    public Optional<Producto> findById(Long id){
        return productRepository.findById(id);
    }
    public Optional<Producto> findByName(String name){
        return productRepository.findByNombre(name);
    }

    @Transactional
    public Optional<Producto> deleteByName(String name){
        return productRepository.deleteByNombre(name);
    }




}
