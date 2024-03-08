package com.backend.integrador.dto.cliente.mapper;

import com.backend.integrador.dto.cliente.UsuarioRegistrarDTO;
import com.backend.integrador.dto.cliente.UsuarioSalidaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.entity.Usuario;
import com.backend.integrador.entity.Reserva;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Usuario toUsuario(UsuarioRegistrarDTO usuarioRegistrarDTO){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRegistrarDTO.getNombre());
        usuario.setApellido(usuarioRegistrarDTO.getApellido());
        usuario.setEmail(usuarioRegistrarDTO.getEmail());
        usuario.setTelefono(usuarioRegistrarDTO.getTelefono());
        usuario.setDireccion(usuarioRegistrarDTO.getDireccion());
        usuario.setCiudad(usuarioRegistrarDTO.getCiudad());
        usuario.setCedula(usuarioRegistrarDTO.getCedula());
        usuario.setReservas(new HashSet<>());
        return usuario;
    }
    public static UsuarioSalidaDTO toUsuarioSalidaDTO(Usuario usuario, Set<Reserva> reservasDelCliente){
        UsuarioSalidaDTO usuarioSalidaDTO = objectMapper.convertValue(usuario, UsuarioSalidaDTO.class);
        Set<ReservaSalidaDTO> reservaSalidaDTOSet = reservasDelCliente.stream()
                .map(reservaCliente -> objectMapper.convertValue(reservaCliente, ReservaSalidaDTO.class))
                        .collect(Collectors.toSet());
        usuarioSalidaDTO.setReservas(reservaSalidaDTOSet);

        return usuarioSalidaDTO;
    }
}
