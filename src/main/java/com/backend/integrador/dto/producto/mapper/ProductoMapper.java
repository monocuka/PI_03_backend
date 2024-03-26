package com.backend.integrador.dto.producto.mapper;

import java.util.List;
import java.util.Set;

import com.backend.integrador.dto.caracteristicas.CaracteristicaDTO;
import com.backend.integrador.dto.categoria.CategoriaDTO;
import com.backend.integrador.dto.imagen.ImagenSalidaDTO;
import com.backend.integrador.dto.producto.ProductoDTO;
import com.backend.integrador.dto.producto.ProductoEntradaDTO;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.entity.Caracteristica;
import com.backend.integrador.entity.Categoria;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class ProductoMapper {
    
    private static  ObjectMapper objectMapper = new ObjectMapper();

    public static ProductoDTO toProductoDTO(Producto producto){
        return objectMapper.convertValue(producto, ProductoDTO.class);
    }

    public static Producto toProducto(ProductoEntradaDTO productoEntradaDTO, Categoria categoria, Set<Caracteristica> caracteristicas){
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
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // ProductoSalidaDTO productoConImagenes = objectMapper.convertValue(producto, ProductoSalidaDTO.class);
        ProductoSalidaDTO productoConImagenes = new ProductoSalidaDTO();
        productoConImagenes.setId(producto.getId());
        productoConImagenes.setNombre(producto.getNombre());
        productoConImagenes.setDescripcion(producto.getDescripcion());
        productoConImagenes.setPrecio(producto.getPrecio());
        CategoriaDTO categoriaDTO = new CategoriaDTO(producto.getCategoria().getId(), producto.getCategoria().getNombre(), producto.getCategoria().getDescripcion());
        productoConImagenes.setCategoria(categoriaDTO);

        List<CaracteristicaDTO> caracteristicasDTO = producto.getCaracteristicas()
                                                                .stream()
                                                                .map( caracteristica -> new CaracteristicaDTO(caracteristica.getId(), caracteristica.getNombre(), caracteristica.getIcono()))
                                                                .toList();
        productoConImagenes.setCaracteristicas(caracteristicasDTO);

        List<ImagenSalidaDTO> imagenesSalida = imagenesDelProducto.stream()
                .map(imagenProducto -> objectMapper.convertValue(imagenProducto, ImagenSalidaDTO.class))
                .toList();
        productoConImagenes.setImagenes(imagenesSalida);
        return productoConImagenes;
    }
}
