package com.backend.integrador.dto.reserva.mapper;

import com.backend.integrador.entity.ImagenProducto;

import java.util.List;

import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ReservaMapper {
     private static  ObjectMapper objectMapper = new ObjectMapper();

     private static IImagenProductoRepository imagenProductoRepository; 

    // Mirar esto una vez terminada las HU de nivel alto, ver si se puede acceder a las imagenes antes de llamar a 
    // toReservaSalidaDTO como se hace en el toProductoSalidaDTO, para no tener que instanciar un IImagenProductoRepository

     public static ReservaSalidaDTO toReservaSalidaDTO(Reserva reserva){
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        ReservaSalidaDTO reservaSalida = new ReservaSalidaDTO();

        List<ImagenProducto> imagenes = imagenProductoRepository.findByProductoId(reserva.getProducto().getId());

        reservaSalida.setId(reserva.getId());
        reservaSalida.setFecha_desde(reserva.getFecha_desde());
        reservaSalida.setFecha_hasta(reserva.getFecha_hasta());
        reservaSalida.setCantidad(reserva.getResd_cantidad());
        reservaSalida.setProducto(ProductoMapper.toProductoSalidaDTO(reserva.getProducto(), imagenes));


        return reservaSalida;
     }
}
