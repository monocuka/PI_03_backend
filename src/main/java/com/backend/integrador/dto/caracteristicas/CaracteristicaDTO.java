package com.backend.integrador.dto.caracteristicas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaracteristicaDTO {
    private Long id;
    private String nombre;
    private String icono;
}
