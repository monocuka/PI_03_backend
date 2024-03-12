package com.backend.integrador.security.service;

import com.backend.integrador.security.dto.AuthResponse;
import com.backend.integrador.security.dto.AuthenticationRequest;
import com.backend.integrador.security.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
	AuthResponse authenticate(AuthenticationRequest request);
}
