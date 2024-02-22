package com.backend.integrador.configurations;

import com.backend.integrador.models.Product;
import com.backend.integrador.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class LoadInitialData {

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void loadData(){
        AtomicLong idCounter = new AtomicLong(1L);
        List<Product> productList = mockData().entrySet().stream()
                .map(entry -> Product.builder()
                        .id(idCounter.getAndIncrement())
                        .name(entry.getKey())
                        .image(entry.getValue())
                        .build())
                .collect(Collectors.toList());
        repository.saveAll(productList);
    }

    private Map<String, String> mockData() {
        return Map.of("pan", "https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "leche","https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "chocolate", "https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "papa", "https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "yuca", "https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "suero", "https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "pescado","https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "arroz", "https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "lenteja", "https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg",
                "papel","https://milrecetas.net/wp-content/uploads/2017/09/pan-integral.jpg");

    }
}
