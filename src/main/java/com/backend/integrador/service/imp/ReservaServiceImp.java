package com.backend.integrador.service.imp;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.dto.reserva.mapper.ReservaMapper;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.backend.integrador.repository.IReservaRepository;
import com.backend.integrador.service.IReservaService;

@Service
public class ReservaServiceImp implements IReservaService{
    
    @Autowired
    private IReservaRepository reservaRepository;
    @Autowired
    private IImagenProductoRepository imagenProductoRepository;

    @Override
    public ReservaSalidaDTO guardarReserva(Reserva reserva){
        List<ImagenProducto> imagenes = imagenProductoRepository.findByProductoId(reserva.getProducto().getId());
        ReservaSalidaDTO reservaSalidaDTO = ReservaMapper.toReservaSalidaDTO(reserva, imagenes);
        reservaRepository.save(reserva);
        return reservaSalidaDTO;
    }

    @Override
    public ReservaSalidaDTO obtenerReservaPorId(Long id){
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        List<ImagenProducto> imagenes = imagenProductoRepository.findByProductoId(reservaOptional.get().getProducto().getId());
        if (reservaOptional.isPresent()) {
            return ReservaMapper.toReservaSalidaDTO(reservaOptional.get(), imagenes);
        } else {
            throw new IllegalArgumentException("Reserva with id " + id + " not found");
        }
    }

    @Override
    public List<ReservaSalidaDTO> obtenerTodosLasReservas(){
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaSalidaDTO> reservasSalida = reservas.stream()
                                                .map(reserva -> {
                                                    return ReservaMapper.toReservaSalidaDTO(reserva, imagenProductoRepository.findByProductoId(reserva.getId()));
                                                })
                                                .toList();
        return reservasSalida;
    }

    @Override
    public void eliminarReserva(Long idReserva){
        Reserva reservaAEliminar = reservaRepository.findById(idReserva).orElse(null);
        if (reservaAEliminar != null) {
            reservaRepository.delete(reservaAEliminar);
        } else {
            System.out.println("No se ha encontrado la reserva con ID " + idReserva); 
        }
    }

}
