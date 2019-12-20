package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.services.StateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateController {

  private StateService stateService;

}
