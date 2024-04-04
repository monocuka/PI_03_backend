package com.backend.integrador.service.imp;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import com.backend.integrador.dto.reserva.ReservaEntradaDTO;
import com.backend.integrador.dto.reserva.ReservaFechasSalidaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaProductoDTO;
import com.backend.integrador.dto.reserva.ReservaUsuarioEmailDTO;
import com.backend.integrador.dto.reserva.mapper.ReservaMapper;
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
    public ReservaSalidaProductoDTO buscarPorProductoId(Long id){
        List<Reserva> reservas = reservaRepository.findByProductoId(id); // buscamos las reservas relacionadas al producto
        List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(id); // buscamos las imagenes del producto
        List<ReservaFechasSalidaDTO> fechasSalida = reservas.stream() // cargamos las fechas del producto en el dto
        .map(reserva -> {
            return ReservaMapper.toFechasSalida(reserva);
        }).toList();
        // mapeamos el producto al salidaDto
        ProductoSalidaDTO productoSalida = ProductoMapper.toProductoSalidaDTO(productoRepository.findById(id).orElse(null), imagenesDelProducto);
        
        ReservaSalidaProductoDTO reservasSalidaDTO = new ReservaSalidaProductoDTO(); // creamos el reservaSalidaDto
        // cargamos el reservaSalidaDTO
        reservasSalidaDTO.setProducto(productoSalida);
        reservasSalidaDTO.setReservas(fechasSalida);


        return reservasSalidaDTO;
    }

    @Override
    public List<ReservaSalidaDTO> obtenerReservasUsuario(ReservaUsuarioEmailDTO email){
        List<Reserva> reservas =  reservaRepository.searchByUsuarioEmail(email.getEmail());
        List<ReservaSalidaDTO> reservasSalida = reservas.stream()
        .map(reserva -> {
            List<ImagenProducto> imagenesDelProducto = imagenProductoRepository.findByProductoId(reserva.getProducto().getId());
            return ReservaMapper.toReservaSalidaDTO(reserva, imagenesDelProducto);
        }).toList();

        return reservasSalida;
    }

    @Override
    public ReservaSalidaDTO guardarReserva(ReservaEntradaDTO reservaEntrada) {
        Producto producto = productoRepository.findById(reservaEntrada.getIdProducto()).orElse(null);
        Usuario usuario = usuarioRepository.findByEmail(reservaEntrada.getEmailUsuario()).orElse(null);
        List<ImagenProducto> imagenes = imagenProductoRepository.findByProductoId(producto.getId());

        Reserva reserva = new Reserva();
        reserva.setCantidad(reservaEntrada.getCantidad());
        reserva.setFecha_desde(reservaEntrada.getFechaDesde());
        reserva.setFecha_hasta(reservaEntrada.getFechaHasta());
        reserva.setProducto(producto);
        reserva.setUsuario(usuario);
        
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
        List<Reserva> reservas = reservaRepository.findAll(); // buscamos todas las reservas
        
        List<ReservaSalidaDTO> reservasSalida = reservas.stream()
                                                .map(reserva -> {
                                                    return ReservaMapper.toReservaSalidaDTO(reserva, imagenProductoRepository.findByProductoId(reserva.getId()));
                                                })
                                                .toList();
        return reservasSalida;
    }

    @Override
    public boolean eliminarReserva(Long idReserva){
        Reserva reservaAEliminar = reservaRepository.findById(idReserva).orElse(null);
        if (reservaAEliminar != null) {
            reservaRepository.delete(reservaAEliminar);
            return true;
        } else {
            System.out.println("No se ha encontrado la reserva con ID " + idReserva); 
            return false;
        }
    }

}
