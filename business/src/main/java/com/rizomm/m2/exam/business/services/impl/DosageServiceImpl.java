package com.rizomm.m2.exam.business.services.impl;

import com.rizomm.m2.exam.business.dto.DosageDto;
import com.rizomm.m2.exam.business.dto.ProductDto;
import com.rizomm.m2.exam.business.dto.StepDto;
import com.rizomm.m2.exam.business.feign.DecisionDefinitionFeignService;
import com.rizomm.m2.exam.business.feign.InStoreFeignService;
import com.rizomm.m2.exam.business.services.DecisionTableService;
import com.rizomm.m2.exam.business.services.DosageService;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.dmn.EvaluateDecisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DosageServiceImpl implements DosageService {

  @Value("${dosage.router.key}")
  private String decisionDefinitionRouterKey;

  private static final String DECISION_RESULT_KEY = "decision";

  private DecisionDefinitionFeignService decisionDefinitionFeignService;
  private DecisionTableService decisionTableService;
  private InStoreFeignService inStoreFeignService;

  @Autowired
  public DosageServiceImpl(
      DecisionDefinitionFeignService decisionDefinitionFeignService,
      DecisionTableService decisionTableService,
      InStoreFeignService inStoreFeignService) {
    this.decisionDefinitionFeignService = decisionDefinitionFeignService;
    this.decisionTableService = decisionTableService;
    this.inStoreFeignService = inStoreFeignService;
  }

  @Override
  public Optional<DosageDto> evaluateDosage(Map<String, String> values) {
    return evaluateDosage(decisionDefinitionRouterKey, values);
  }

  @Override
  public Optional<DosageDto> evaluateDosage(String decisionDefinitionKey,
      Map<String, String> values) {

    // evaluate router
    EvaluateDecisionDto evaluateDecisionForFirst = decisionTableService
        .getEvaluateDecisionForFirst(decisionDefinitionRouterKey, values);

    List<Map<String, VariableValueDto>> routerResult = decisionDefinitionFeignService
        .evaluate(decisionDefinitionRouterKey, evaluateDecisionForFirst);

    String decisionTableName = routerResult.get(0).get(DECISION_RESULT_KEY).getValue().toString();

    EvaluateDecisionDto dosageInputs = decisionTableService
        .getEvaluateDecisionForCollect(decisionTableName, values);
    List<Map<String, VariableValueDto>> dosageResult = decisionDefinitionFeignService
        .evaluate(decisionTableName, dosageInputs);

    Map<String, List<VariableValueDto>> stepsMap = dosageResult
        .stream()
        .map(stringVariableValueDtoMap -> stringVariableValueDtoMap.entrySet().iterator().next())
        .collect(Collectors
            .groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())));

    DosageDto dosageDto = new DosageDto();
    stepsMap.forEach((s, variableValueDtos) -> {
      StepDto stepDto = new StepDto();
      variableValueDtos.forEach(variableValueDto -> {
        ProductDto productDto = new ProductDto();
        productDto.setSuperModel(variableValueDto.getValue().toString());
        stepDto.getProducts().add(productDto);
      });
      stepDto.setName(s);
      dosageDto.getSteps().add(stepDto);
    });

    inStoreFeignService.getSuperModel("toto", "toto");

    return Optional.of(dosageDto);
  }

}
