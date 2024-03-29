package com.backend.integrador.dto.reserva.mapper;

import com.backend.integrador.entity.ImagenProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ReservaMapper {
   
   private static  ObjectMapper objectMapper = new ObjectMapper();

   
   public static ReservaSalidaDTO toReservaSalidaDTO(Reserva reserva, List<ImagenProducto> imagenes){
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        ReservaSalidaDTO reservaSalida = new ReservaSalidaDTO();

        reservaSalida.setFecha_desde(reserva.getFecha_desde());
        reservaSalida.setFecha_hasta(reserva.getFecha_hasta());
        reservaSalida.setProducto(ProductoMapper.toProductoSalidaDTO(reserva.getProducto(), imagenes));


        return reservaSalida;
     }
}
