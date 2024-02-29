package com.backend.integrador.service;

import com.backend.integrador.Entity.ImagenProducto;
import com.backend.integrador.Entity.Producto;
import com.backend.integrador.repository.ImagenProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        //final String pathInicial = System.getProperty("user.dir"); // armar la ruta para quien sea que comparta pantalla
        //ej: "C:\Proyecto integrador\Front\Proyecto_integrador_e3\src\assets\img"
        // "C:" + File.separator + "Proyecto Integrador" + File.separator + "Front" + File.separator + "Proyecto_integrador_e3" +  File.separator + "src" + File.separator + "assets" + File.separator + "img" + File.separator"
        //final String pathProyecto = File.separator + "src" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + filename;
        ///home/nahuel/Desktop/Proyecto Integrador/Front/Proyecto_integrador_e3/src/assets
        final String pathProyecto = File.separator + "home" + File.separator + "nahuel" + File.separator + "Desktop" + File.separator + "Proyecto Integrador" + File.separator + "Front" + File.separator + "Proyecto_integrador_e3" + File.separator + "src" + File.separator + "assets" + File.separator + "img" + File.separator + filename;
        ///home/nahuel/Desktop/                                                                                                             Proyecto Integrador/Front/Proyecto_integrador_e3/src/assets/img
        //final String pathCompleto = pathInicial + pathProyecto;
        ImagenProducto i = new ImagenProducto(p, filename, "");
        Path path = Paths.get(pathProyecto);

        try{
            Files.write(path, imagen.getBytes());
            System.out.println(path);
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