package com.backend.integrador.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.backend.integrador.service.IImagenProductoService;

@Service
public class ImagenProductoServiceImp implements IImagenProductoService{

    @Autowired
    private IImagenProductoRepository imagenRepository;
    
    @Override
    public ImagenProducto guardaImagenProducto(MultipartFile imagen, Producto producto) {
        String fileName = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
        // Ruta del projecto
        // final String pathInicial = System.getProperty("user.dir");
        final String pathCompleto = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator;
        // final String pathCompleto = "C:" + File.separator +"Imagenes" + File.separator + fileName;
        Path path = Paths.get(pathCompleto);
        try {
            // Guardar la imagen en una carpeta de archivos
            Files.write(path, imagen.getBytes());
            // Creación de la imagen
            ImagenProducto img = new ImagenProducto();
            img.setProducto(producto);
            img.setUrlImagen("localhost:8080/api/imagenes/"+fileName);
            return imagenRepository.save(img);
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public ImagenProducto obtenerImagenProductoPorId(Long id) {
        return imagenRepository.findById(id).orElse(null);
    }

    @Override
    public List<ImagenProducto> obtenerTodosLasImagenProducto() {
        return imagenRepository.findAll();
    }

    @Override
    public void eliminarImagenProducto(Long idImagenProducto) {
        ImagenProducto imagenProductoAEliminar = imagenRepository.findById(idImagenProducto).orElse(null);
        if (imagenProductoAEliminar != null){
            imagenRepository.delete(imagenProductoAEliminar);
        }else{      
            System.out.println("No se ha encontrado el producto con ID " + idImagenProducto); 
        }
    }
    
    public FileInputStream obtenerImagen(String nombre) throws IOException {
        // Ruta de directorio donde se encuentran las imágenes
        final String directorioImagenes = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator;
        

        // Combinar la ruta del directorio con el nombre de la imagen
        String rutaImagen = directorioImagenes + nombre;

        // Crear un objeto File con la ruta de la imagen
        File imagen = new File(rutaImagen);

        // Verificar si la imagen existe
        if (!imagen.exists()) {
            throw new IOException("La imagen no existe");
        }

        // Devolver un FileInputStream para la imagen
        return new FileInputStream(imagen);
    }
}
