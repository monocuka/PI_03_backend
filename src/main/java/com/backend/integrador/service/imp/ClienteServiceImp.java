package com.backend.integrador.service.imp;

import java.util.List;

import com.backend.integrador.dto.cliente.UsuarioRegistrarDTO;
import com.backend.integrador.dto.cliente.UsuarioSalidaDTO;
import com.backend.integrador.dto.cliente.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integrador.entity.Usuario;
import com.backend.integrador.repository.IClienteRepository;
import com.backend.integrador.service.IClienteService;


@Service
public class ClienteServiceImp implements IClienteService{
    
    @Autowired
    private IClienteRepository clienteRepository;
    
    @Override
    public UsuarioSalidaDTO guardaCliente(UsuarioRegistrarDTO clienteDTO) {
        Usuario usuario = UsuarioMapper.toUsuario(clienteDTO);
        UsuarioSalidaDTO usuarioSalidaDTO = UsuarioMapper.toUsuarioSalidaDTO(clienteRepository.save(usuario), usuario.getReservas());
        return usuarioSalidaDTO;
    }

    @Override
    public Usuario obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<UsuarioSalidaDTO> obtenerTodosLosClientes() {
        List<Usuario> listaUsuarios = clienteRepository.findAll();

        return listaUsuarios.stream()
                .map(usuario -> {
                    return UsuarioMapper.toUsuarioSalidaDTO(usuario, usuario.getReservas());
                })
                .toList();
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        Usuario usuarioAEliminar = clienteRepository.findById(idCliente).orElse(null);
        if (usuarioAEliminar != null){
            clienteRepository.delete(usuarioAEliminar);
        }else{      
            System.out.println("No se ha encontrado el producto con ID " + idCliente); 
        }
    }

    @Override
    public UsuarioSalidaDTO buscarPorEmail(String email){
        Usuario usuario = clienteRepository.findByEmail(email);
        if (usuario == null){
            return null;
        }
        return UsuarioMapper.toUsuarioSalidaDTO(usuario, usuario.getReservas());
    }
    
}
