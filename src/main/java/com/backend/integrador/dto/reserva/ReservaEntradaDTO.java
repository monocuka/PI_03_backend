package com.backend.integrador.dto.reserva;

import java.time.LocalDate;

import com.backend.integrador.dto.producto.ProductoEntradaDTO;
import com.backend.integrador.dto.usuario.UsuarioEntradaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaEntradaDTO {
    private LocalDate fecha_desde;
    private LocalDate fecha_hasta;
    private int cantidad; // ??
    private ProductoEntradaDTO producto;
    private UsuarioEntradaDTO usuario; 
}
