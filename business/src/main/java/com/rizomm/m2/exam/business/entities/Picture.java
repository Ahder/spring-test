package com.rizomm.m2.exam.business.entities;

import java.util.List;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Picture {

  @Id
  private Long id;

  @ElementCollection
  private Map<String, String> sources;

  @ElementCollection
  private List<String> labels;

}
