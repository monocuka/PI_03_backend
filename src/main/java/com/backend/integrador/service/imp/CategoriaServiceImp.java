package com.backend.integrador.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.dto.caracteristicas.mapper.CategoriaMapper;
import com.backend.integrador.dto.categoria.CategoriaEntradaDTO;
import com.backend.integrador.dto.categoria.CategoriaSalidaDTO;
import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.entity.Categoria;
import com.backend.integrador.repository.ICategoriaRepository;
import com.backend.integrador.service.ICategoriaService;


@Service
public class CategoriaServiceImp implements ICategoriaService{

    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Override
    public CategoriaSalidaDTO guardaCategoria(CategoriaEntradaDTO categoriaEntradaDTO) {
        Categoria categoriaGuardada  = categoriaRepository.save( CategoriaMapper.toCategoria(categoriaEntradaDTO) );
        return CategoriaMapper.toSalidaDTO(categoriaGuardada);
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
