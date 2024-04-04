package com.backend.integrador.dto.usuario.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.integrador.dto.rol.RolSalidaDTO;
import com.backend.integrador.dto.rol.mapper.RolMapper;
import com.backend.integrador.dto.usuario.UsuarioEntradaDTO;
import com.backend.integrador.dto.usuario.UsuarioListarSalidaDTO;
import com.backend.integrador.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioMapper {
    private static  ObjectMapper objectMapper = new ObjectMapper();

    public static UsuarioListarSalidaDTO toListarSalidaDTO(Usuario usuario){
        UsuarioListarSalidaDTO usuarioSalida = new UsuarioListarSalidaDTO();
        
        usuarioSalida.setId(usuario.getId());
        usuarioSalida.setName(usuario.getName());
        usuarioSalida.setEmail(usuario.getEmail());
        usuarioSalida.setLast_name(usuario.getLastName());

        List<RolSalidaDTO> rolesSalida = usuario.getRoles().stream()
        .map(rol -> RolMapper.toRolSalidaDTO(rol)) 
        .collect(Collectors.toList());
    
        usuarioSalida.setRoles(rolesSalida);

        return usuarioSalida;
    }

    public static Usuario toUsuario(UsuarioEntradaDTO usuario){
        return   objectMapper.convertValue(usuario, Usuario.class);
    }
}
