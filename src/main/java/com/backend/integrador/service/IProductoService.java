package com.backend.integrador.service;

import java.util.List;
import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.producto.ProductoSalidaBusquedaSimilar;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.entity.Producto;
//import com.backend.integrador.entity.Producto;
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
    public Producto chequearDisponibilidad(Long id, LocalDate fechaInicial, LocalDate fechaFinal);
    public List<ReservaSalidaDTO> listarReservas(); // borrar este metodo esta solo para ver si se cargaron las reservas correctamente
    // public List<ProductoSalidaDTO> buscarProductosFechas(String busqueda, LocalDate desde, LocalDate hasta);
}
