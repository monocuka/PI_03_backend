package com.backend.integrador.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.producto.ProductoSalidaBusquedaSimilar;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IProductoService {
    public ProductoSalidaDTO guardarProducto(String productoStr, MultipartFile imagen)throws DuplicateKeyException, JsonProcessingException;
    public ProductoSalidaDTO obtenerProductoPorId(Long id);
    public List<ProductoSalidaDTO> obtenerTodosLosProductos();
    public void eliminarProducto(Long idProducto);
    public ProductoSalidaDTO modificarProducto(String productoStr, MultipartFile imagen)throws Exception;
    public List<ProductoSalidaDTO> obtenerProductosAleatorios();
    public List<ProductoSalidaBusquedaSimilar> buscarProductosSimilares(String busqueda);
    public List<ProductoSalidaDTO> productosDisponiblesFechas(LocalDate fechaInicial, LocalDate fechaFinal);
    public List<ProductoSalidaDTO> productosDisponiblesFechasYNombre(LocalDate fechaInicial, LocalDate fechaFinal, String busqueda);
    public List<ProductoSalidaDTO> buscarPorCategoria(Long id);
    
}
