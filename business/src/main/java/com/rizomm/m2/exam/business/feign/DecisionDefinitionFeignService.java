package com.rizomm.m2.exam.business.feign;

import com.rizomm.m2.exam.business.dto.InputDto;
import java.util.List;
import java.util.Map;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.dmn.EvaluateDecisionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${feign.decision.url}", name = "decisionDefinition")
public interface DecisionDefinitionFeignService {

  @PostMapping(value = "/rest/decision-definition/key/{decisionDefinitionKey}/evaluate", consumes = "application/json")
  List<Map<String, VariableValueDto>> evaluate(@PathVariable String decisionDefinitionKey,
      @RequestBody EvaluateDecisionDto evaluateDecisionDto);

  @GetMapping(value = "/decision_definitions/key/{decisionDefinitionKey}/inputs", consumes = "application/json")
  List<InputDto> getDecisionDefinitionInputs(@PathVariable String decisionDefinitionKey);
}
