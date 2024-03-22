package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.entity.Reserva;

public interface IReservaService {
    public ReservaSalidaDTO guardarReserva(Reserva reserva);
    public ReservaSalidaDTO obtenerReservaPorId(Long id);
    public List<ReservaSalidaDTO> obtenerTodosLasReservas();
    public void eliminarReserva(Long idReserva);
}
