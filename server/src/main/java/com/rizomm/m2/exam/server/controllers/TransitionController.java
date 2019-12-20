package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.services.TransitionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transitions")
public class TransitionController {

  private TransitionService transitionService;
}
