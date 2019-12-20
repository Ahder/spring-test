package com.rizomm.m2.exam.business.dto.instore;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class PictureDto {

  Map<String, String> sources = new HashMap<>();

  String id;

}
