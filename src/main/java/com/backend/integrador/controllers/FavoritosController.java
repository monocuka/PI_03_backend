package com.backend.integrador.controllers;

import com.backend.integrador.dto.favoritos.FavoritoDTO;
import com.backend.integrador.entity.Favorito;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.repository.IFavoritoRepository;
import com.backend.integrador.service.imp.FavoritoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritoServiceImp serviceImp;

    @GetMapping("/{userId}")
    public List<Producto> getFavoritos(@PathVariable Long userId){
        return serviceImp.getFavByUser(userId);
    }

    @PostMapping
    public String save(@RequestBody FavoritoDTO fav){
        try {
            serviceImp.saveFav(fav);
            return "ok";
        } catch (Exception e){
            return "fail";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteFav(@PathVariable Long id){
        try {
            serviceImp.delete(id);
            return "ok";
        } catch (Exception e){
            return "fail";
        }
    }
}
