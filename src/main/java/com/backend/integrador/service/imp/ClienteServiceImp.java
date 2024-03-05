package com.backend.integrador.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.entity.Cliente;
import com.backend.integrador.repository.IClienteRepository;
import com.backend.integrador.service.IClienteService;


@Service
public class ClienteServiceImp implements IClienteService{
    
    @Autowired
    private IClienteRepository clienteRepository;
    
    @Override
    public Cliente guardaCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        Cliente clienteAEliminar = clienteRepository.findById(idCliente).orElse(null);
        if (clienteAEliminar != null){
            clienteRepository.delete(clienteAEliminar);
        }else{      
            System.out.println("No se ha encontrado el producto con ID " + idCliente); 
        }
    }
    
}
