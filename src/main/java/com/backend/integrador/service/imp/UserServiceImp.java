package com.backend.integrador.service.imp;

import com.backend.integrador.entity.User;
import com.backend.integrador.dto.UserDto;
import com.backend.integrador.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp {

    private UserRepository userRepository;

    public User create(UserDto user){
        return userRepository.save(buildUser(user));
    }

    private User buildUser(UserDto user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return  User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .build();
    }
}
