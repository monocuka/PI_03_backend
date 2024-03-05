package com.backend.integrador.dto.imagen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propiedades desconocidas durante la deserialización
public class ImagenSalidaDTO {
    private Long id;
    private  String urlImagen;
}
