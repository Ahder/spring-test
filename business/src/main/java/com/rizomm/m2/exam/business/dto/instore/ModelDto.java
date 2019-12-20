package com.rizomm.m2.exam.business.dto.instore;

import java.util.List;
import lombok.Data;

@Data
public class ModelDto {

  private String id;

  private Boolean sellable;

  private String name;

  private List<PictureDto> pictures;
}
