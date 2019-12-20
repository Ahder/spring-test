package com.rizomm.m2.exam.business.feign;

import com.rizomm.m2.exam.business.dto.instore.SuperModelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${feign.instore.url}", name = "inStore")
public interface InStoreFeignService {

  @GetMapping(value = "/v1/search/super_models/{id}/stores/{store_id}", consumes = "application/json")
  public SuperModelDto getSuperModel(@PathVariable String id, @PathVariable(name = "store_id") String storeId);
}
