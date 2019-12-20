package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.dto.DosageDto;
import com.rizomm.m2.exam.business.services.DosageService;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dosages")
public class DosageController {

  private DosageService dosageService;

  @Autowired
  public DosageController(
      DosageService dosageService) {
    this.dosageService = dosageService;
  }

  @PostMapping
  public ResponseEntity<DosageDto> evaluateDosage(@RequestBody Map<String, String> values) {
    Optional<DosageDto> dosageDto = dosageService.evaluateDosage(values);

    return dosageDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

  }


}
