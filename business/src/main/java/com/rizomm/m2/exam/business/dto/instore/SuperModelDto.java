package com.rizomm.m2.exam.business.dto.instore;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SuperModelDto {

  List<ModelDto> models = new ArrayList<>();
}
