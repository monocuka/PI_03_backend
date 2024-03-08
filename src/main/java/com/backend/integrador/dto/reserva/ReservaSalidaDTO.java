package com.backend.integrador.dto.reserva;

import com.backend.integrador.dto.cliente.UsuarioSalidaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaSalidaDTO {
    private Date fecha_desde;
    private Date fecha_hasta;

    private UsuarioSalidaDTO cliente;

    // private Set<ReservaDetalle> reservaDetalles
}
