package com.backend.integrador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> buscarReservaPorProductoId(@PathVariable Long id){
        List<ReservaSalidaDTO> reservasSalida =  reservaService.buscarPorProductoId(id);
        if (reservasSalida.isEmpty() || reservasSalida == null) {
        return new ResponseEntity<>("Este producto no tiene reservas.", HttpStatus.NOT_FOUND);
    } else {
        return ResponseEntity.ok().body(reservasSalida);
    }
    }
}
