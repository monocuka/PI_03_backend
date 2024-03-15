package com.backend.integrador.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.backend.integrador.security.filter.JWTFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTFilter jwtFilter;
	private final AuthenticationProvider authenticationProvider;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//http.csrf(AbstractHttpConfigurer::disable);
		//http.csrf(csrf->csrf.disable());
		// http.cors( cors -> corsConfigurationSource())
		http
			.csrf( AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth->auth
			.requestMatchers(getPublicEndpoints()).permitAll()
			.anyRequest().authenticated())
			.sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.csrf(csrf->csrf.disable());
		
		return http.build();
	}
	
	private RequestMatcher getPublicEndpoints() {
		
		// return new OrRequestMatcher(
		// 	new AntPathRequestMatcher("/api/auth/**"),
		// 	new AntPathRequestMatcher("/api/producto/listar"),
		// 	new AntPathRequestMatcher("/api/producto/id/{id}"),
		// 	new AntPathRequestMatcher("/api/imagenes/**")
		// 	);
		return new OrRequestMatcher(
			new AntPathRequestMatcher("**")
			);
	}

	// 	@Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //     final CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOrigins(List.of("*"));
    //     configuration.setAllowedMethods(List.of("*"));
    //     configuration.setAllowCredentials(true);
    //     configuration.setAllowedHeaders(List.of("Authorization", "Cache-control", "Content-type"));
    //     final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }

}

