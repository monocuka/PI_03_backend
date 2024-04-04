package com.backend.integrador.security.service;

import org.springframework.dao.DuplicateKeyException;

import com.backend.integrador.security.dto.AuthResponse;
import com.backend.integrador.security.dto.AuthenticationRequest;
import com.backend.integrador.security.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request)throws DuplicateKeyException;
	AuthResponse authenticate(AuthenticationRequest request);
}
