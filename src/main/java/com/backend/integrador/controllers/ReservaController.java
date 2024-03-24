package com.backend.integrador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.service.IReservaService;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    
    @Autowired
    private IReservaService reservaService;

    @GetMapping("/listar")
    public List<ReservaSalidaDTO> listarReservas(){
        return reservaService.obtenerTodosLasReservas();
    }
}
