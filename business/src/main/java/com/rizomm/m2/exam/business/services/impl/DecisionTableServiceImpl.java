package com.rizomm.m2.exam.business.services.impl;

import com.rizomm.m2.exam.business.dto.InputDto;
import com.rizomm.m2.exam.business.feign.DecisionDefinitionFeignService;
import com.rizomm.m2.exam.business.services.DecisionTableService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.dmn.EvaluateDecisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecisionTableServiceImpl implements DecisionTableService {

  private DecisionDefinitionFeignService decisionDefinitionFeignService;

  @Autowired
  public DecisionTableServiceImpl(
      DecisionDefinitionFeignService decisionDefinitionFeignService) {
    this.decisionDefinitionFeignService = decisionDefinitionFeignService;
  }

  @Override
  public EvaluateDecisionDto getEvaluateDecisionForFirst(String decisionDefinitionKey,
      Map<String, String> values) {

    List<InputDto> inputs = decisionDefinitionFeignService
        .getDecisionDefinitionInputs(decisionDefinitionKey);

    Map<String, VariableValueDto> variables = new HashMap<>();

    values.forEach((s, s2) -> {

      InputDto inputDto = inputs.stream().filter(inputDto1 -> inputDto1.getName().equals(s)).findFirst().orElse(new InputDto());

      VariableValueDto variableValueDto = new VariableValueDto();
      variableValueDto.setValue(s2);
      if(inputDto.getType() != null) {
        variableValueDto.setType(inputDto.getType());
      } else {
        variableValueDto.setType("string");
      }


      variables.put(s, variableValueDto);

    });

    EvaluateDecisionDto evaluateDecisionDto = new EvaluateDecisionDto();
    evaluateDecisionDto.setVariables(variables);

    return evaluateDecisionDto;
  }

  @Override
  public EvaluateDecisionDto getEvaluateDecisionForCollect(String decisionDefinitionKey,
      Map<String, String> values) {

    List<InputDto> inputs = decisionDefinitionFeignService
        .getDecisionDefinitionInputs(decisionDefinitionKey);

    Map<String, VariableValueDto> variables = new HashMap<>();

    inputs.forEach(inputDto -> {
      VariableValueDto variableValueDto = new VariableValueDto();
      String s = values.get(inputDto.getName());

      variableValueDto.setValue(s);
      variableValueDto.setType(inputDto.getType());

      variables.put(inputDto.getName(), variableValueDto);
    });

    EvaluateDecisionDto evaluateDecisionDto = new EvaluateDecisionDto();
    evaluateDecisionDto.setVariables(variables);

    return evaluateDecisionDto;

  }
}
