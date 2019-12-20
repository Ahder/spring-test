package com.rizomm.m2.exam.business.dto;

import java.util.List;
import lombok.Data;

@Data
public class TransitionDto {

  private Long id;

  private String value;

  private String phrase;

  private Long from;

  private Long to;

  private List<PictureDto> pictures;

}
