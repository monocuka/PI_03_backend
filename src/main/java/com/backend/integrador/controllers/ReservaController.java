package com.backend.integrador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.integrador.dto.reserva.ReservaEntradaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaDTO;
import com.backend.integrador.dto.reserva.ReservaSalidaProductoDTO;
import com.backend.integrador.dto.reserva.ReservaUsuarioEmailDTO;
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

    @GetMapping("/listarUsuariosReserva")
    public List<ReservaSalidaDTO> listarUsuarioReserva(@RequestBody ReservaUsuarioEmailDTO email){
        return reservaService.obtenerReservasUsuario(email);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> buscarReservaPorProductoId(@PathVariable Long id){
        ReservaSalidaProductoDTO reservasSalida =  reservaService.buscarPorProductoId(id);
        if ( reservasSalida == null) {
            return new ResponseEntity<>("Este producto no tiene reservas.", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(reservasSalida);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarReserva(@RequestBody ReservaEntradaDTO reserva){
        reservaService.guardarReserva(reserva);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id){
        boolean isDeleted = reservaService.eliminarReserva(id);
        if (isDeleted) {
            return ResponseEntity.ok("Reserva con ID " + id + " se borro correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la reserva con ID " + id);
        }
    }
}
