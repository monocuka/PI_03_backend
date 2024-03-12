package com.backend.integrador.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integrador.dto.UserDto;
import com.backend.integrador.entity.User;
import com.backend.integrador.service.imp.UserServiceImp;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {
    
    private UserServiceImp userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody UserDto body){
      return userService.create(body);
    }
}
