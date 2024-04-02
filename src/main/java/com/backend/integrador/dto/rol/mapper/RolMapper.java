package com.backend.integrador.dto.rol.mapper;

import com.backend.integrador.dto.rol.RolEntradaDTO;
import com.backend.integrador.dto.rol.RolSalidaDTO;
import com.backend.integrador.entity.Rol;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RolMapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static RolSalidaDTO toRolSalidaDTO(Rol rol){
        return  objectMapper.convertValue(rol, RolSalidaDTO.class);
    }
    public static Rol toRol(RolEntradaDTO rol){
        return  objectMapper.convertValue(rol, Rol.class);
    }
}
