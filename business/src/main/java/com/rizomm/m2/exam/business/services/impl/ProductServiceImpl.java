package com.rizomm.m2.exam.business.services.impl;

import com.rizomm.m2.exam.business.entities.Product;
import com.rizomm.m2.exam.business.repositories.ProductRepository;
import com.rizomm.m2.exam.business.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long prodId)  {
        return productRepository.findById(prodId);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> deleteProduct(Long prodId)  {
         Optional<Product> product = productRepository.findById(prodId);
         if (product.isPresent()) {
             productRepository.delete(product.get());
         }
         return product;
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
            productRepository.save(existingProduct.get());
        }
        return existingProduct;
    }
}
