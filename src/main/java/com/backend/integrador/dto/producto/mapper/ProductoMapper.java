package com.backend.integrador.dto.producto.mapper;

import java.util.List;

import com.backend.integrador.dto.imagen.ImagenSalidaDTO;
import com.backend.integrador.dto.producto.ProductoDTO;
import com.backend.integrador.dto.producto.ProductoEntradaDTO;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.entity.Caracteristica;
import com.backend.integrador.entity.Categoria;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ProductoMapper {
    
    private static  ObjectMapper objectMapper = new ObjectMapper();

    public static ProductoDTO toProductoDTO(Producto producto){
        return objectMapper.convertValue(producto, ProductoDTO.class);
    }

    public static Producto toProducto(ProductoEntradaDTO productoEntradaDTO, Categoria categoria, List<Caracteristica> caracteristicas){
        Producto producto = new Producto();
        producto.setNombre(productoEntradaDTO.getNombre());
        producto.setDescripcion(productoEntradaDTO.getDescripcion());
        producto.setPrecio(productoEntradaDTO.getPrecio());
        producto.setCategoria(categoria);
        producto.setCaracteristicas(caracteristicas);
        return producto;
    }

    public static Producto toProducto(ProductoSalidaDTO productoImagenesDTO){
        return objectMapper.convertValue(productoImagenesDTO, Producto.class);
    }

    public static ProductoSalidaDTO toProductoSalidaDTO(Producto producto, List<ImagenProducto> imagenesDelProducto){
        ProductoSalidaDTO productoConImagenes = objectMapper.convertValue(producto, ProductoSalidaDTO.class);
        List<ImagenSalidaDTO> imagenesSalida = imagenesDelProducto.stream()
                .map(imagenProducto -> objectMapper.convertValue(imagenProducto, ImagenSalidaDTO.class))
                .toList();
        productoConImagenes.setImagenes(imagenesSalida);
        return productoConImagenes;
    }
}
