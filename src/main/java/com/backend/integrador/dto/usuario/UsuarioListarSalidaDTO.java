package com.backend.integrador.dto.usuario;

import java.util.List;

import com.backend.integrador.dto.rol.RolSalidaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListarSalidaDTO {
    private Long id;
    private String name;
    private String email;
    private String last_name;
    private List<RolSalidaDTO> roles;
}
