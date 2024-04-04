package com.backend.integrador.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.dto.rol.mapper.RolMapper;
import com.backend.integrador.dto.usuario.UsuarioEntradaDTO;
import com.backend.integrador.dto.usuario.UsuarioListarSalidaDTO;
import com.backend.integrador.dto.usuario.mapper.UsuarioMapper;
import com.backend.integrador.entity.Rol;
import com.backend.integrador.entity.Usuario;
import com.backend.integrador.repository.IUsuarioRepository;
import com.backend.integrador.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
      
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Override
    public List<UsuarioListarSalidaDTO> obtenerTodosLosUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
        .map(UsuarioMapper::toListarSalidaDTO).toList();
    }

    @Override
    public UsuarioListarSalidaDTO cambiarRol(String usuario) throws Exception{
         ObjectMapper objectMapper = new ObjectMapper();
         UsuarioEntradaDTO usuarioEntrada = objectMapper.readValue(usuario, UsuarioEntradaDTO.class);
         System.out.println("[cambiarRol] " + usuarioEntrada);
        Usuario usuarioCambiar = usuarioRepository.findById(usuarioEntrada.getId()).orElse(null);
        if(usuarioCambiar == null){
            throw new Exception("No existe el usuario.");
        }
        List<Rol> rolesNuevos = usuarioEntrada.getRoles().stream()
        .map(RolMapper::toRol).toList();
       
        usuarioCambiar.setRoles(new ArrayList<>(rolesNuevos));

        usuarioRepository.save(usuarioCambiar);
        return UsuarioMapper.toListarSalidaDTO(usuarioCambiar);
    }
}
