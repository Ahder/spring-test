package com.rizomm.m2.exam.business.services;

import com.rizomm.m2.exam.business.dto.DosageDto;
import java.util.Map;
import java.util.Optional;

public interface DosageService {

  Optional<DosageDto> evaluateDosage(Map<String, String> values);

  Optional<DosageDto> evaluateDosage(String decisionDefinitionKey, Map<String, String> values);

}
