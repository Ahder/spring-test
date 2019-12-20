package com.rizomm.m2.exam.business.config;

import com.rizomm.m2.exam.business.dto.TransitionDto;
import com.rizomm.m2.exam.business.entities.Transition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.rizomm.m2.exam.business.feign"})
@ComponentScan(basePackages = {"com.rizomm.m2.exam.business"})
public class BusinessConfig {

  @Bean
  public ModelMapper modelMapper() {

    ModelMapper modelMapper = new ModelMapper();

    PropertyMap<Transition, TransitionDto> propertyMap = new PropertyMap<Transition, TransitionDto>() {

      @Override
      protected void configure() {
        map().setFrom(source.getFrom().getId());
        map().setTo(source.getTo().getId());
      }
    };

    modelMapper.addMappings(propertyMap);

    return modelMapper;
  }

}
