package com.backend.integrador.service;

import com.backend.integrador.Entity.ImagenProducto;
import com.backend.integrador.Entity.Producto;
import com.backend.integrador.repository.ImagenProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenProductoService {

    @Autowired
    private ImagenProductoRepository ImagenProductoRepository;


    public ImagenProducto guardarImagenProducto(Producto p, MultipartFile imagen){
        String filename = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
        // Ruta del projecto
        final String pathInicial = System.getProperty("user.dir");
        final String pathProyecto = "/src/main/resources/static/images/" + filename;
        final String pathCompleto = pathInicial + pathProyecto;
        ImagenProducto i = new ImagenProducto(p, pathProyecto, "");
        Path path = Paths.get(pathCompleto);

        try{
            Files.write(path, imagen.getBytes());
            return ImagenProductoRepository.save(i);
        } catch (IOException e) {
            return null;
        }


    }
    @Transactional
    public void actualizarImagenProducto(ImagenProducto i){
        ImagenProductoRepository.save(i);
    }
    @Transactional
    public void EliminarImagenProducto(int id){
        ImagenProductoRepository.deleteById(id);
    }
    public Optional<ImagenProducto> buscarPorid(int id){
        return ImagenProductoRepository.findById(id);
    }

    public List<ImagenProducto> listarImagenes(){
        return ImagenProductoRepository.findAll();
    }


}