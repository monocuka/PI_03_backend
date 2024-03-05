package com.backend.integrador.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;

public interface IImagenProductoService {
    public ImagenProducto guardaImagenProducto(MultipartFile imagen, Producto producto);
    public ImagenProducto obtenerImagenProductoPorId(Long id);
    public List<ImagenProducto> obtenerTodosLasImagenProducto();
    public void eliminarImagenProducto(Long idImagenProducto);
    public FileInputStream obtenerImagen(String nombre) throws IOException;
}
