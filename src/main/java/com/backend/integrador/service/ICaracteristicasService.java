package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.entity.Caracteristica;

public interface ICaracteristicasService {
    public Caracteristica guardarCaracteristica(Caracteristica caracteristica);
    public Caracteristica obtenerCaracteristicaPorId(Long id);
    public List<Caracteristica> obtenerTodosLasCarcateristicas();
    public void eliminarCategoria(Long idCaracteristica);
}
