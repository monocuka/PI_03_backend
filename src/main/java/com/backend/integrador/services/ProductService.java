package com.backend.integrador.services;

import com.backend.integrador.models.Product;
import com.backend.integrador.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAllRandomOrder(){
        return productRepository.findAllRandomOrder();
    }
}
