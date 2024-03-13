package com.backend.integrador.security.dto;

import java.util.List;

import com.backend.integrador.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String name;
    private String lastName;
    private String token;
    private List<Rol> roles;
}
