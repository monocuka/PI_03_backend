package com.backend.integrador.security;

import com.backend.integrador.Entity.User;
import lombok.AllArgsConstructor;
import com.backend.integrador.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("El usuario con email:"+ email + " no existe"));
    return new UserDetailsImpl(user);
  }
}
