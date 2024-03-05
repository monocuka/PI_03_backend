package com.backend.integrador.service;

import java.util.List;

import com.backend.integrador.entity.Cliente;

public interface IClienteService {
    public Cliente guardaCliente(Cliente cliente);
    public Cliente obtenerClientePorId(Long id);
    public List<Cliente> obtenerTodosLosClientes();
    public void eliminarCliente(Long idCliente);
}

