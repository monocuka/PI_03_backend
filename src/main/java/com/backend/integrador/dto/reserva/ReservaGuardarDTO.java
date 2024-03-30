package com.backend.integrador.dto.reserva;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaGuardarDTO {
    private LocalDate fecha_desde;
    private LocalDate fecha_hasta;
    private int cantidad;
    private Long id_producto;
    private Long id_usuario;
}
