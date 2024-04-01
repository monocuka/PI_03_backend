package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.dto.reserva.ReservaEntradaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;

public interface IReservaService {
    public ReservaSalidaDTO guardarReserva(ReservaEntradaDTO reserva);
    public ReservaSalidaDTO obtenerReservaPorId(Long id);
    public List<ReservaSalidaDTO> obtenerTodosLasReservas();
    public void eliminarReserva(Long idReserva);
    public List<ReservaSalidaDTO> buscarPorProductoId(Long id);
    public List<ReservaSalidaDTO> obtenerReservasUsuario(String email);
}
