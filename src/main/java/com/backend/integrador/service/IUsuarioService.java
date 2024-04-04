package com.backend.integrador.service;

import java.util.List;


import com.backend.integrador.dto.usuario.UsuarioListarSalidaDTO;


public interface IUsuarioService {
    public List<UsuarioListarSalidaDTO> obtenerTodosLosUsuarios();
    public UsuarioListarSalidaDTO cambiarRol(String usuarioStr) throws Exception;
}
