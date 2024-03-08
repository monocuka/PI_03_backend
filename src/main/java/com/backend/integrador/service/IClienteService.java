package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.dto.cliente.UsuarioRegistrarDTO;
import com.backend.integrador.dto.cliente.UsuarioSalidaDTO;
import com.backend.integrador.entity.Usuario;

public interface IClienteService {
    public UsuarioSalidaDTO guardaCliente(UsuarioRegistrarDTO cliente);
    public Usuario obtenerClientePorId(Long id);
    public List<UsuarioSalidaDTO> obtenerTodosLosClientes();
    public void eliminarCliente(Long idCliente);

    Usuario buscarPorEmail(String email);
}

