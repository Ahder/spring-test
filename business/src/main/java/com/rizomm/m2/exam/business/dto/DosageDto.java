package com.rizomm.m2.exam.business.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class DosageDto {

  private List<StepDto> steps = new ArrayList<>();

}
