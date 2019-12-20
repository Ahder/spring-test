package com.rizomm.m2.exam.server;

import com.rizomm.m2.exam.business.config.BusinessConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(BusinessConfig.class)
public class ExamApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExamApplication.class, args);
  }

}
