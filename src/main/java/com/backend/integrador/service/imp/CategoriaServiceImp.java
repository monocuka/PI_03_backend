package com.backend.integrador.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.entity.Categoria;
import com.backend.integrador.repository.ICategoriaRepository;
import com.backend.integrador.service.ICategoriaService;


@Service
public class CategoriaServiceImp implements ICategoriaService{

    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Override
    public Categoria guardaCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return  categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> obtenerTodosLosCategorias() {
        return categoriaRepository.findAll();
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
