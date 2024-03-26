package com.backend.integrador.dto.favoritos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoDTO {
    private Long userId;
    private Long productId;
}
