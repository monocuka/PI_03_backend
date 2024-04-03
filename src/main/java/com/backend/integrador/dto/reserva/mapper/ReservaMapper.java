package com.backend.integrador.dto.reserva.mapper;

import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;

import java.util.List;

import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import com.backend.integrador.dto.reserva.ReservaEntradaDTO;
import com.backend.integrador.dto.reserva.ReservaFechasSalidaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.entity.Usuario;
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

   public static Reserva toReserva(ReservaEntradaDTO reservaEntrada, Producto producto, Usuario usuario){
      Reserva reserva = new Reserva();

      reserva.setCantidad(reservaEntrada.getCantidad());
      reserva.setFecha_desde(reservaEntrada.getFechaDesde());
      reserva.setFecha_hasta(reservaEntrada.getFechaHasta());
      reserva.setUsuario(usuario);
      reserva.setProducto(producto);

      return reserva;
   }

   public static ReservaFechasSalidaDTO toFechasSalida(Reserva reserva){
      ReservaFechasSalidaDTO fechasSalida = new ReservaFechasSalidaDTO();

      fechasSalida.setFechaDesde(reserva.getFecha_desde());
      fechasSalida.setFechaHasta(reserva.getFecha_hasta());

      return fechasSalida;
   }
}
