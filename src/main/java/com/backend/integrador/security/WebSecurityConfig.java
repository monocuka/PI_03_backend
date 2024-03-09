package com.backend.integrador.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean // este endpoing es para omitirlo
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/test/**");
    }

  @Bean // es por quien se van a enviar los token y recibir el que maneja los filtos
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
    jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
    jwtAuthenticationFilter.setFilterProcessesUrl("/login");

    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable) // Cross-site Request Forgery
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated() ) // reglas de las request
        .httpBasic(Customizer.withDefaults())
            .sessionManagement(httpSecuritySessionManagementConfigurer -> {
              httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            }).formLogin(Customizer.withDefaults())
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)// habilitamos la autenticacion basica para que podamos enviar un usuario y una contraseña (esto luego se va a desabilitar)
        .build();
  }

  @Bean
  public UserDetailsService userDetailsService(){// es un usuario como tal el que tiene permiso o no a acceder a los servicios de la api
    // para autenticarnos contra una base de datos hay que realizar esta implementacion con el aceseso a la base de datos
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin")).roles().build());
    return manager;
  }

  public AuthenticationManager authManager(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
     authenticationManagerBuilder.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());
     return authenticationManagerBuilder.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
