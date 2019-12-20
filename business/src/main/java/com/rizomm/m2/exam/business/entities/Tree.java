package com.rizomm.m2.exam.business.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Tree {

  @Id
  private Long id;

  @OneToMany
  private List<State> states;

  @OneToMany
  private List<Transition> transitions;

  @OneToMany
  private List<Picture> pictures;
}
