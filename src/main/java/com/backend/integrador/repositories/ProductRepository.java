package com.backend.integrador.repositories;

import com.backend.integrador.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT * FROM products ORDER BY RAND()", nativeQuery = true)
    List<Product> findAllRandomOrder();
}
