package com.backend.integrador.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.producto.ProductoSalidaBusquedaSimilar;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IProductoService {
    public ProductoSalidaDTO guardarProducto(String productoStr, MultipartFile imagen)throws JsonMappingException, JsonProcessingException;
    public ProductoSalidaDTO obtenerProductoPorId(Long id);
    public List<ProductoSalidaDTO> obtenerTodosLosProductos();
    public void eliminarProducto(Long idProducto);
    public ProductoSalidaDTO modificarProducto(String productoStr, MultipartFile imagen)throws Exception;
    public List<ProductoSalidaDTO> obtenerProductosAleatorios();
    public List<ProductoSalidaBusquedaSimilar> buscarProductosSimilares(String busqueda);
    // public List<ProductoSalidaDTO> buscarProductosFechas(String busqueda, LocalDate desde, LocalDate hasta);
}
