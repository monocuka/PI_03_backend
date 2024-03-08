package com.backend.integrador.controllers;


import com.backend.integrador.dto.cliente.UsuarioLoginDTO;
import com.backend.integrador.dto.cliente.UsuarioRegistrarDTO;
import com.backend.integrador.dto.cliente.UsuarioSalidaDTO;
import com.backend.integrador.entity.Usuario;
import com.backend.integrador.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cliente")
public class UsuarioController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/")
    public List<UsuarioSalidaDTO> obtenerClientes(){
        return clienteService.obtenerTodosLosClientes();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarCliente(@RequestBody UsuarioRegistrarDTO cliente){
        if (cliente == null) {
            return ResponseEntity.badRequest().body("The ClienteRegistrarDTO object is null");
        }

        Usuario existingCliente = clienteService.buscarPorEmail(cliente.getEmail());
        if (existingCliente != null) {
            return ResponseEntity.badRequest().body("A user with this email already exists");
        }

        UsuarioSalidaDTO createdCliente = clienteService.guardaCliente(cliente);

        if (createdCliente == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Cliente");
        }

        return ResponseEntity.ok(createdCliente);
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO usuario){
        Usuario usuarioExistente = clienteService.buscarPorEmail(usuario.getEmail());

        if (usuarioExistente != null && passwordEncoder.matches(usuario.getContrasena(), usuarioExistente.getContrasena())){
            // crear token
            String token = jwtTokenProvider.createToken(usuarioExistente);
            System.out.println("El usuario existe, token creado: " + token);

            // Devolver el token en la respuesta
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            // Las credenciales son incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo electrónico o contraseña inválidos");
        }
    }*/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO usuario){
        Usuario usuarioExistente = clienteService.buscarPorEmail(usuario.getEmail());

        if (usuarioExistente != null && usuario.getContrasena().equals(usuarioExistente.getContrasena())){
            // crear token falso
            String token = "Token falso";
            System.out.println("El usuario existe, token creado: " + token);

            // Devolver el token falso en la respuesta
            return ResponseEntity.ok(token);
        } else {
            // Las credenciales son incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo electrónico o contraseña inválidos");
        }
    }

}
