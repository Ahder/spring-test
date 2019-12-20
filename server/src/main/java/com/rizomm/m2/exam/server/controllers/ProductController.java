package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.converters.ProductConverter;
import com.rizomm.m2.exam.business.dto.ProductDto;
import com.rizomm.m2.exam.business.entities.Product;
import com.rizomm.m2.exam.business.services.ProductService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;
  private ProductConverter productConverter;

  public ProductController(
      ProductService productService,
      ProductConverter productConverter) {
    this.productService = productService;
    this.productConverter = productConverter;
  }


  @GetMapping
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    List<Product> products = productService.findAll();
    List<ProductDto> productDtos = new ArrayList<>();

    for (Product product: products) {
      productDtos.add(productConverter.convert(product));
    }
    return ResponseEntity.ok(productDtos);
  }

  @GetMapping("{prodId}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Long prodId) {

    Optional<Product> product = productService.findById(prodId);
    if(product.isPresent()) {
      return ResponseEntity.ok(productConverter.convert(product.get()));
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<ProductDto> AddProduct(@RequestBody ProductDto productDto) {
    return new ResponseEntity<>(productConverter.convert(productService.createProduct(productConverter.convert(productDto))), HttpStatus.CREATED);
  }

  @DeleteMapping("{prodId}")
  public ResponseEntity deleteProduct(@PathVariable Long prodId)  {
    Optional<Product> product = productService.deleteProduct(prodId);
    if(product.isPresent()) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<ProductDto> editProduct(@RequestBody ProductDto productDto) {
    Optional<Product> product = productService.updateProduct(productConverter.convert(productDto));
    if(product.isPresent()) {
      return ResponseEntity.ok(productConverter.convert(product.get()));
    }
    return ResponseEntity.notFound().build();
  }

}
