package com.rizomm.m2.exam.business.services;

import java.util.Map;
import org.camunda.bpm.engine.rest.dto.dmn.EvaluateDecisionDto;

public interface DecisionTableService {

  EvaluateDecisionDto getEvaluateDecisionForFirst(String decisionDefinitionKey, Map<String, String> values);

  EvaluateDecisionDto getEvaluateDecisionForCollect(String decisionDefinitionKey, Map<String, String> values);

}
