package com.backend.integrador.dto.reserva;

import com.backend.integrador.dto.producto.ProductoSalidaDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaSalidaProductoDTO {
    private List<ReservaFechasSalidaDTO> reservas;
    private ProductoSalidaDTO producto;
}
