package com.backend.integrador.controllers;

import com.backend.integrador.entity.User;
import com.backend.integrador.dto.UserDto;
import com.backend.integrador.service.imp.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

  private UserServiceImp userService;

  @GetMapping("/findAll")
  public String findAll(){
    return "hola";
  }

  @PostMapping("/signUp")
  public User createUser(@RequestBody UserDto body){
    return userService.create(body);
  }

}
