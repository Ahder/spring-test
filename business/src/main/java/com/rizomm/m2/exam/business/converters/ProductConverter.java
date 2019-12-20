package com.rizomm.m2.exam.business.converters;

import com.rizomm.m2.exam.business.dto.ProductDto;
import com.rizomm.m2.exam.business.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

  private ModelMapper modelMapper;

  public ProductConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public ProductDto convert(Product product) {
    return modelMapper.map(product, ProductDto.class);
  }

  public Product convert(ProductDto productDto) {
    return modelMapper.map(productDto, Product.class);
  }

}
