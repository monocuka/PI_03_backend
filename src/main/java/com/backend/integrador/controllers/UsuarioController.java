package com.backend.integrador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integrador.dto.usuario.UsuarioEntradaDTO;
import com.backend.integrador.dto.usuario.UsuarioListarSalidaDTO;
import com.backend.integrador.service.IUsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/listar")
    public List<UsuarioListarSalidaDTO> listarUsuarios(){
        List<UsuarioListarSalidaDTO> listaUsuarios = usuarioService.obtenerTodosLosUsuarios();
        return listaUsuarios;
    }

    @PostMapping(value = "/cambiarRol", consumes = { "multipart/form-data" })
    public ResponseEntity<?> cambiarRol(@RequestParam("usuario") String usuarioStr) throws Exception{
        System.out.println("[Controller cambiarRol] " + usuarioStr);
        UsuarioListarSalidaDTO usuarioSalida = usuarioService.cambiarRol(usuarioStr);
        return ResponseEntity.ok(usuarioSalida);
    }
    
}
