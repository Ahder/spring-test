package com.rizomm.m2.exam.business.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Product {

  @Id
  private Long id;

  private String superModel;

}
