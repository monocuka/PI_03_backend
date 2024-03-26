package com.backend.integrador.dto.reserva;

import java.time.LocalDate;

import com.backend.integrador.dto.producto.ProductoSalidaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaSalidaDTO {
    private Long id;
    private LocalDate fecha_desde;
    private LocalDate fecha_hasta;
    private int cantidad;
    private ProductoSalidaDTO producto;
}
