package com.backend.integrador.service.imp;

import java.util.Collections;
import java.util.List;

import com.backend.integrador.excepciones.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.dto.producto.ProductoDTO;
import com.backend.integrador.dto.producto.ProductoEntradaDTO;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import com.backend.integrador.entity.Categoria;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.backend.integrador.repository.IProductoRepository;
import com.backend.integrador.service.ICategoriaService;
import com.backend.integrador.service.IImagenProductoService;
import com.backend.integrador.service.IProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ProductoServiceImp implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private IImagenProductoRepository  imagenProductoRepository;
    @Autowired
    private IImagenProductoService imagenProductoServices;    
    @Autowired
    private ICategoriaService categoriaService;

    @Override
    public ProductoSalidaDTO guardarProducto(String productoStr, MultipartFile imagen) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductoEntradaDTO productoEntradaDTO = objectMapper.readValue(productoStr, ProductoEntradaDTO.class);
            Categoria categoria = categoriaService.obtenerCategoriaPorId(productoEntradaDTO.getCategoria().getId());
            Producto productoGuardado = productoRepository.save(ProductoMapper.toProducto(productoEntradaDTO, categoria));
            ImagenProducto imagenConProducto = imagenProductoServices.guardaImagenProducto(imagen, productoGuardado);
            List<ImagenProducto> listaDeImagenes = Collections.singletonList(imagenConProducto);
            return ProductoMapper.toProductoSalidaDTO(productoGuardado, listaDeImagenes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ProductoSalidaDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if  (producto == null){ return null; }
        List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(id);
        return ProductoMapper.toProductoSalidaDTO(producto, imagenesDelProducto);
    }

    @Override
    public List<ProductoSalidaDTO> obtenerTodosLosProductos() {
        List<Producto> todoslosProductos = productoRepository.findAll();

        return todoslosProductos.stream()
                .map(producto -> {
                    List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(producto.getId());
                    return ProductoMapper.toProductoSalidaDTO(producto, imagenesDelProducto);
                })
                .toList();
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        Producto productoAEliminar = productoRepository.findById(idProducto).orElse(null);
        if (productoAEliminar != null){
            productoRepository.delete(productoAEliminar);
        }else{      
            System.out.println("No se ha encontrado el producto con ID " + idProducto); 
        }
    }

    @Override
    public ProductoSalidaDTO modificarProducto(String productoStr, MultipartFile imagen) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductoEntradaDTO productoEntradaDTO = objectMapper.readValue(productoStr, ProductoEntradaDTO.class);
            Categoria categoria = categoriaService.obtenerCategoriaPorId(productoEntradaDTO.getCategoria().getId()); // preguntarle a juan, el sobre el comment en CategoriaEntradaDTO
            Producto productoGuardado = productoRepository.save(ProductoMapper.toProducto(productoEntradaDTO, categoria));
            ImagenProducto imagenConProducto = imagenProductoServices.guardaImagenProducto(imagen, productoGuardado);
            List<ImagenProducto> listaDeImagenes = Collections.singletonList(imagenConProducto);
            return ProductoMapper.toProductoSalidaDTO(productoGuardado, listaDeImagenes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ProductoSalidaDTO buscarPorNombre(String nombre){
        Producto producto = productoRepository.findByNombre(nombre);
        List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(producto.getId());
        if (producto == null) {
            throw new ResourceNotFoundException("Product with name " + nombre + " not found");
        }
        return ProductoMapper.toProductoSalidaDTO(producto, imagenesDelProducto);
    }
   
}