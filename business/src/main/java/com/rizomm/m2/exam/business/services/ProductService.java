package com.rizomm.m2.exam.business.services;

import com.rizomm.m2.exam.business.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long prodId);

    Product createProduct(Product product);

    Optional<Product> deleteProduct(Long prodId);

    Optional<Product> updateProduct(Product product);
}
