package com.backend.integrador.dto.reserva;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaEntradaDTO {
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private int cantidad;
    private Long idProducto;
    private Long idUsuario;
}
