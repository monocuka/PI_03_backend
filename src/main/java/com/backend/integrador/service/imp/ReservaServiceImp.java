package com.backend.integrador.service.imp;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.dto.reserva.ReservaEntradaDTO;
import com.backend.integrador.dto.reserva.ReservaGuardarDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.dto.reserva.mapper.ReservaMapper;
import com.backend.integrador.entity.Caracteristica;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.entity.Usuario;
import com.backend.integrador.repository.ICaracteristicasRepository;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.backend.integrador.repository.IProductoRepository;
import com.backend.integrador.repository.IReservaRepository;
import com.backend.integrador.repository.IUsuarioRepository;
import com.backend.integrador.service.IReservaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReservaServiceImp implements IReservaService{
    
    @Autowired
    private IReservaRepository reservaRepository;
    @Autowired
    private IImagenProductoRepository imagenProductoRepository;
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ICaracteristicasRepository caracteristicasRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Override
    public List<ReservaSalidaDTO> buscarPorProductoId(Long id){
        List<Reserva> reservas = reservaRepository.findByProductoId(id);
        List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(id);
        List<ReservaSalidaDTO> reservasSalidaDTO = reservas.stream()
        .map(reserva -> {
            return ReservaMapper.toReservaSalidaDTO(reserva, imagenesDelProducto);
        })
        .toList();
        return reservasSalidaDTO;
    }

    @Override
    public ReservaSalidaDTO guardarReserva(String reservaEntrada) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ReservaGuardarDTO reservaGuardar = objectMapper.readValue(reservaEntrada, ReservaGuardarDTO.class);
        Producto producto = productoRepository.findById(reservaGuardar.getId_producto()).orElse(null);
        Usuario usuario = usuarioRepository.findById(reservaGuardar.getId_usuario()).orElse(null);
        List<ImagenProducto> imagenes = imagenProductoRepository.findByProductoId(producto.getId());

        Reserva reserva = new Reserva();

        reserva.setCantidad(reservaGuardar.getCantidad());
        reserva.setFecha_desde(reservaGuardar.getFecha_desde());
        reserva.setFecha_hasta(reservaGuardar.getFecha_hasta());
        reserva.setProducto(producto);
        reserva.setUsuario(usuario);
        
        //Reserva reserva = ReservaMapper.toReserva(reservaEntrada, producto, null);
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
