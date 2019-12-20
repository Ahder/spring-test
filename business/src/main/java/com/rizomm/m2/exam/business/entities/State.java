package com.rizomm.m2.exam.business.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class State {

  @Id
  private Long id;

  private String type;

  private String phrase;

  private String name;

  @OneToMany
  private List<Picture> pictures;

}
