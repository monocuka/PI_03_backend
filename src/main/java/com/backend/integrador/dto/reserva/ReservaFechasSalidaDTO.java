package com.backend.integrador.dto.reserva;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaFechasSalidaDTO {
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
