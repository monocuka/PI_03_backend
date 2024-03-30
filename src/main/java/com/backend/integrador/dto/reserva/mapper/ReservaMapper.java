package com.backend.integrador.dto.reserva.mapper;

import com.backend.integrador.entity.Caracteristica;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;

import java.util.List;
import java.util.Set;

import com.backend.integrador.dto.categoria.mapper.CategoriaMapper;
import com.backend.integrador.dto.producto.mapper.ProductoMapper;
import com.backend.integrador.dto.reserva.ReservaEntradaDTO;
import com.backend.integrador.dto.reserva.ReservaGuardarDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.dto.usuario.mapper.UsuarioMapper;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ReservaMapper {
   
   private static  ObjectMapper objectMapper = new ObjectMapper();

   
   public static ReservaSalidaDTO toReservaSalidaDTO(Reserva reserva, List<ImagenProducto> imagenes){
      objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

      ReservaSalidaDTO reservaSalida = new ReservaSalidaDTO();

      System.out.println("[Mapper]" + reserva.getFecha_desde());
      System.out.println("[Mapper]" + reserva.getFecha_hasta());
      reservaSalida.setFecha_desde(reserva.getFecha_desde());
      reservaSalida.setFecha_hasta(reserva.getFecha_hasta());
      reservaSalida.setProducto(ProductoMapper.toProductoSalidaDTO(reserva.getProducto(), imagenes));


      return reservaSalida;
   }

   public static Reserva toReserva(ReservaGuardarDTO reservaGuardar, Producto producto, Usuario usuario){
      Reserva reserva = new Reserva();

      reserva.setCantidad(reservaGuardar.getCantidad());
      reserva.setFecha_desde(reservaGuardar.getFecha_desde());
      reserva.setFecha_hasta(reservaGuardar.getFecha_hasta());
      reserva.setUsuario(usuario);
      reserva.setProducto(producto);

      return reserva;
   }
}
