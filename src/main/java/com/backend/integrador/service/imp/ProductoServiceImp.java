package com.backend.integrador.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.backend.integrador.dto.producto.ProductoDTO;
import com.backend.integrador.dto.producto.ProductoEntradaDTO;
import com.backend.integrador.dto.producto.ProductoSalidaBusquedaSimilar;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.dto.reserva.mapper.ReservaMapper;
import com.backend.integrador.entity.Caracteristica;
import com.backend.integrador.entity.Categoria;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.repository.ICaracteristicasRepository;
import com.backend.integrador.repository.ICategoriaRepository;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.backend.integrador.repository.IProductoRepository;
import com.backend.integrador.repository.IReservaRepository;
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
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private ICaracteristicasRepository caracteristicasRepository;
    @Autowired
    private IReservaRepository reservaRepository;


    @Override
    public List<ProductoSalidaDTO> buscarPorCategoria(Long id){
        List<Producto> productosCategoria = productoRepository.findByCategoriaId(id);
        List<ProductoSalidaDTO> productosCategoriaSalida = productosCategoria.stream()
        .map(producto -> {
            return ProductoMapper.toProductoSalidaDTO(producto, imagenProductoRepository.findByProductoId(id));
        })
        .toList();
        return productosCategoriaSalida;
    }

    @Override
    public List<ProductoSalidaDTO>  productosDisponiblesFechas(LocalDate fechaInicial, LocalDate fechaFinal){
        // filtro todos los productos por un rango de fechas
        List<Producto> productosDisponibles = reservaRepository.filtrarProductoPorRangoFechas(fechaInicial, fechaFinal); 
        List<ProductoSalidaDTO> productosDisponiblesDTO = productosDisponibles.stream()
                                                                              .map(producto -> {
                                                                                return ProductoMapper.toProductoSalidaDTO(producto, imagenProductoRepository.findByProductoId(producto.getId()));
                                                                              })
                                                                              .toList();
        return productosDisponiblesDTO; 
    }

    @Override
    public List<ProductoSalidaDTO> productosDisponiblesFechasYNombre(LocalDate fechaInicial, LocalDate fechaFinal, String busqueda){
        List<Producto> productosDisponibles = reservaRepository.filtrarProductosPorRangoFechasYNombre(fechaInicial, fechaFinal, busqueda);
        List<ProductoSalidaDTO> productosDisponiblesDTO = productosDisponibles.stream()
                                                                              .map(producto -> {
                                                                                return ProductoMapper.toProductoSalidaDTO(producto, imagenProductoRepository.findByProductoId(producto.getId()));
                                                                              })
                                                                              .toList();
        return productosDisponiblesDTO;
    }

    @Override
    public ProductoSalidaDTO guardarProducto(String productoStr, MultipartFile imagen) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductoEntradaDTO productoEntradaDTO = objectMapper.readValue(productoStr, ProductoEntradaDTO.class);
            // Obtengo la categoria
            Categoria categoria = categoriaRepository.findById(productoEntradaDTO.getCategoria().getId()).orElse(null);
            // Obtengo las caracteristicas
            Set<Caracteristica> caracteristicas = productoEntradaDTO.getCaracteristicas()
                                                        .stream()
                                                        .map(caracteristica -> caracteristicasRepository.findById(caracteristica.getId()).orElse(null))
                                                        .filter(Objects::nonNull) 
                                                        .collect(Collectors.toSet());
            // Se guarda el producto
            Producto productoGuardado = productoRepository.save(ProductoMapper.toProducto(productoEntradaDTO, categoria, caracteristicas));
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
    public ProductoSalidaDTO modificarProducto(String productoStr, MultipartFile imagen) throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ProductoDTO productoDTO = objectMapper.readValue(productoStr, ProductoDTO.class);
            Producto productoAModificar = productoRepository.findById(productoDTO.getId()).orElse(null);

            if  (productoAModificar == null) {
                throw new Exception ("El producto no existe");
            }

            productoAModificar.setNombre(productoDTO.getNombre());
            productoAModificar.setDescripcion(productoDTO.getDescripcion());
            productoAModificar.setPrecio(productoDTO.getPrecio());

            // obtengo la categoria y modifico el actual
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoria().getId()).orElse(null);
            productoAModificar.setCategoria(categoria);

            // obtengo las caracteristicas y modifico el actual
            Set<Caracteristica> caracteristicas = productoDTO.getCaracteristicas()
                                                 .stream()
                                                 .map(caracteristica -> caracteristicasRepository.findById(caracteristica.getId()).orElse(null))
                                                 .filter(Objects::nonNull) // Filtrar valores null
                                                 .collect(Collectors.toSet());

            productoAModificar.setCaracteristicas(caracteristicas);
            
            productoRepository.save(productoAModificar);
            
            List<ImagenProducto> listaDeImagenes = new ArrayList<>();
            
            // Si tiene imagen la agrego
            if(imagen != null){
                ImagenProducto imagenConProducto = imagenProductoServices.guardaImagenProducto(imagen, productoAModificar);
                listaDeImagenes = Collections.singletonList(imagenConProducto);
            }
            
            return ProductoMapper.toProductoSalidaDTO(productoAModificar, listaDeImagenes);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Exception ("Error en estructura del Json");
        }
    }

    @Override
    public List<ProductoSalidaDTO> obtenerProductosAleatorios() {
        List<Producto> listaDeProductosAleatorios = productoRepository.obtenerProductosAleatorios();
        return listaDeProductosAleatorios.stream()
                .map(producto -> {
                    List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(producto.getId());
                    return ProductoMapper.toProductoSalidaDTO(producto, imagenesDelProducto);
                })
                .toList();
    }

    // Como maximo devuelve 4 productos para no llenar la pagina web
    @Override
    public List<ProductoSalidaBusquedaSimilar> buscarProductosSimilares(String busqueda) {
        List<Producto> listaDeProductosBuscados = productoRepository.findFirst4ByNombreContainingIgnoreCase(busqueda);
        return listaDeProductosBuscados.stream()
                                        .map( producto -> new ProductoSalidaBusquedaSimilar( producto.getId(), producto.getNombre()) )
                                        .toList();
    }

    // @Override
    // public Producto buscarPorNombre(String nombre){
    //     return productoRepository.findByNombre(nombre);
    // }

    // public List<ProductoSalidaDTO> buscarProductosFechas(String busqueda, LocalDate desde, LocalDate hasta){
    //     List<Producto> productosValidos = productoRepository.buscarProductosFechas(desde, hasta, busqueda);

    //     return productosValidos.stream()
    //     .map(producto -> {
    //         List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(producto.getId());
    //         return ProductoMapper.toProductoSalidaDTO(producto, imagenesDelProducto);
    //     })
    //     .toList();

    // }
   
}