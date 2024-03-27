package com.backend.integrador.dto.usuario;

import java.util.List;

import com.backend.integrador.dto.rol.RolEntradaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntradaDTO {
    private Long id;
    private String email;
    private List<RolEntradaDTO> roles;
}