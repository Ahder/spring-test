package com.rizomm.m2.exam.business.repositories;

import com.rizomm.m2.exam.business.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
