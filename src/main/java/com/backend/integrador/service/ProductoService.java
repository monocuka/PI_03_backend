package com.backend.integrador.service;

import com.backend.integrador.Entity.Producto;
import com.backend.integrador.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Producto guardarProducto(Producto p){
        return productoRepository.save(p);
    }

//    public void actualizarProducto(Producto p){
//        return productoRepository.save(p);
//    }

    @Transactional
    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id){
        return productoRepository.findById(id);
    }
    public Optional<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }

    @Transactional
    public Optional<Producto> eliminarPorNombre(String nombre){
        return productoRepository.deleteByNombre(nombre);
    }




}
