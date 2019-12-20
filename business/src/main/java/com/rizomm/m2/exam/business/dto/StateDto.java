package com.rizomm.m2.exam.business.dto;

import java.util.List;
import lombok.Data;

@Data
public class StateDto {

  private Long id;

  private String type;

  private String phrase;

  private String name;

  private List<PictureDto> pictures;

}
