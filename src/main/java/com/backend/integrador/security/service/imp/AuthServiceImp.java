package com.backend.integrador.security.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.integrador.entity.Rol;
import com.backend.integrador.entity.Usuario;
import com.backend.integrador.repository.IUsuarioRepository;
import com.backend.integrador.security.dto.AuthResponse;
import com.backend.integrador.security.dto.AuthenticationRequest;
import com.backend.integrador.security.dto.RegisterRequest;
import com.backend.integrador.security.service.AuthService;
import com.backend.integrador.security.service.JWTService;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
    private final PasswordEncoder passwordEncoder;
	private final IUsuarioRepository usuarioRepository;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;
	
	
	@Override
	public AuthResponse register(RegisterRequest request) throws DuplicateKeyException {
		List<Rol> roles = new ArrayList<>();
		Rol rol = new Rol();
		rol.setNombreRol("ROLE_USER");
		roles.add(rol);
		var user = Usuario.builder()
				.name(request.getName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roles(roles)
				.build();
		
		try{
			Usuario usuarioGuardado = usuarioRepository.save(user);
			var jwtToken = jwtService.generateToken(user);
			return AuthResponse.builder()
								.name(usuarioGuardado.getName())
								.lastName(usuarioGuardado.getLastName())
								.email(usuarioGuardado.getEmail())
								.token(jwtToken)
								.roles(roles)
								.build();

		} catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("El usuario ya se encuentra registrado", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al registrar el usuario", e);
        }
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		System.out.println("Autenticación");
		System.out.println(request.getEmail());
		System.out.println(request.getPassword());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder()
							.name(user.getName())
							.lastName(user.getLastName())
							.email(user.getEmail())
						   	.token(jwtToken)
							.roles(user.getRoles())
						   	.build();
	}
}

