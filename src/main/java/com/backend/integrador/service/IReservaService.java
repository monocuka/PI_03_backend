package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.dto.reserva.ReservaEntradaDTO;
import com.backend.integrador.dto.reserva.ReservaGuardarDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IReservaService {
    public ReservaSalidaDTO guardarReserva(String reserva)throws JsonProcessingException;
    public ReservaSalidaDTO obtenerReservaPorId(Long id);
    public List<ReservaSalidaDTO> obtenerTodosLasReservas();
    public void eliminarReserva(Long idReserva);
    public List<ReservaSalidaDTO> buscarPorProductoId(Long id);
}
