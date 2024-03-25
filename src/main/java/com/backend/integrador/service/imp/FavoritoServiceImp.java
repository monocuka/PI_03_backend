package com.backend.integrador.service.imp;

import com.backend.integrador.dto.favoritos.FavoritoDTO;
import com.backend.integrador.entity.Favorito;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.entity.Usuario;
import com.backend.integrador.repository.IFavoritoRepository;
import com.backend.integrador.repository.IProductoRepository;
import com.backend.integrador.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FavoritoServiceImp {

    @Autowired
    private IFavoritoRepository FavRepository;

    @Autowired
    private IUsuarioRepository usrRepository;

    @Autowired
    private IProductoRepository productRepository;

    public List<Producto> getFavByUser(Long userId){
        return FavRepository.findProductosFavoritosPorUsuario(userId);
    }
    public void saveFav(FavoritoDTO fav) {

        Usuario usr = usrRepository.findById(fav.getUserId()).orElse(null);
        Producto producto = productRepository.findById(fav.getProductId()).orElse(null);
        if (Objects.nonNull(usr) && Objects.nonNull(producto)) {
            Favorito newFav = new Favorito(usr, producto);
            FavRepository.save(newFav);
            return;
        }
        throw new RuntimeException("usr or product not exist");
    }

    public void delete(Long id){
        FavRepository.deleteById(id);
    }

}
