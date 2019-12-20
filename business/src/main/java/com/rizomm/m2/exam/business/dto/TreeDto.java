package com.rizomm.m2.exam.business.dto;

import java.util.List;
import lombok.Data;

@Data
public class TreeDto {

  private Long id;

  private List<PictureDto> pictures;

  private List<StateDto> states;

  private List<TransitionDto> transitions;

}
