package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.entity.Reserva;

public interface IReservaService {
    public Reserva guardaReserva(Reserva reserva);
    public Reserva obtenerReservaPorId(Long id);
    public List<Reserva> obtenerTodosLasReservas();
    public void eliminarReserva(Long idReserva);
}
