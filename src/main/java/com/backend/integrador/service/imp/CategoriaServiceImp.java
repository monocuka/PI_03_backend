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

import com.backend.integrador.dto.categoria.CategoriaEntradaDTO;
import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.dto.categoria.mapper.CategoriaMapper;
import com.backend.integrador.entity.Categoria;
import com.backend.integrador.repository.ICategoriaRepository;
import com.backend.integrador.service.ICategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CategoriaServiceImp implements ICategoriaService{

    @Autowired
    private ICategoriaRepository categoriaRepository;
    

    @Override
    public CategoriaSalidaDTO modificarCategoria(String categoriaStr, MultipartFile imagen) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            System.out.println(categoriaStr);
            CategoriaEntradaDTO categoriaEntradaDTO = objectMapper.readValue(categoriaStr, CategoriaEntradaDTO.class);
            Categoria categoriaPorActualizar = categoriaRepository.findById(categoriaEntradaDTO.getId()).orElse(null);

            if(categoriaPorActualizar == null){
                throw new Exception ("El producto no existe");
            }
            categoriaPorActualizar.setDescripcion(categoriaEntradaDTO.getDescripcion());
            categoriaPorActualizar.setUrlImagen(guardarImagenCategoria(imagen));
            categoriaPorActualizar.setNombre(categoriaEntradaDTO.getNombre());
            categoriaRepository.save(categoriaPorActualizar);
            
            return CategoriaMapper.toSalidaDTO(categoriaPorActualizar);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Exception ("Error en estructura del Json");
        }
    }

    @Override
    public CategoriaSalidaDTO guardaCategoria(String categoriaStr, MultipartFile imagen) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CategoriaEntradaDTO categoriaEntradaDTO = objectMapper.readValue(categoriaStr, CategoriaEntradaDTO.class);
        Categoria categoriaPorGuardar = CategoriaMapper.toCategoria(categoriaEntradaDTO);
        categoriaPorGuardar.setUrlImagen(guardarImagenCategoria(imagen));
        Categoria categoriaGuardada  = categoriaRepository.save(categoriaPorGuardar );
       
        return CategoriaMapper.toSalidaDTO(categoriaGuardada);
    }

    private String guardarImagenCategoria(MultipartFile imagen){
        String fileName = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
        final String pathCompleto = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + "categoria" + File.separator + fileName;
        
        Path path = Paths.get(pathCompleto);
        try {
            // Guardar la imagen en una carpeta de archivos
            Files.write(path, imagen.getBytes());
            // Creación de la imagen
           
            return "http://localhost:8080/api/categoria/imagen/"+fileName;
        } catch (IOException e) {
            return "Fallo la creacion de la imagen";
        }
    }
    @Override
    public FileInputStream obtenerImagen(String nombre) throws IOException{
        final String directorioImagenes = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + "categoria" + File.separator;
        
        String rutaImagen = directorioImagenes + nombre;

        File imagen =  new File(rutaImagen);

        if (!imagen.exists()) {
            throw new IOException("La imagen no existe");
        }
        return new FileInputStream(imagen);
    }

    @Override
    public CategoriaSalidaDTO obtenerCategoriaPorId(Long id) {
        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElse(null);
        return CategoriaMapper.toSalidaDTO(categoriaEncontrada);
    }

    @Override
    public List<CategoriaSalidaDTO> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map( categoria -> CategoriaMapper.toSalidaDTO(categoria)).toList();
    }

    @Override
    public void eliminarCategoria(Long idCategoria) {
        Categoria categoriaAEliminar = categoriaRepository.findById(idCategoria).orElse(null);
        if (categoriaAEliminar != null){
            categoriaRepository.delete(categoriaAEliminar);
        }else{      
            System.out.println("No se ha encontrado el producto con ID " + idCategoria); 
        }
    }
    
}
