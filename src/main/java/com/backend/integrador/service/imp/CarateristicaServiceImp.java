package com.backend.integrador.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.entity.Caracteristica;
import com.backend.integrador.repository.ICaracteristicasRepository;
import com.backend.integrador.service.ICaracteristicasService;

@Service
public class CarateristicaServiceImp implements ICaracteristicasService{

    @Autowired
    ICaracteristicasRepository caracteristicasRepository;
    @Override
    public Caracteristica guardarCaracteristica(Caracteristica caracteristica) {
        return caracteristicasRepository.save(caracteristica);
    }

    @Override
    public Caracteristica obtenerCaracteristicaPorId(Long id) {
        return caracteristicasRepository.findById(id).orElse(null);
    }

    @Override
    public List<Caracteristica> obtenerTodosLasCarcateristicas() {
        return caracteristicasRepository.findAll();
    }

    @Override
    public void eliminarCategoria(Long idCaracteristica) {
        Caracteristica caracteristicaABorrar = caracteristicasRepository.findById(idCaracteristica).orElse(null);
        if( caracteristicaABorrar != null ){
            caracteristicasRepository.delete(caracteristicaABorrar);
        } else{
            System.out.println("No se ha encontrado la caracteristica con ID " + idCaracteristica);
        }
    }
    
}
