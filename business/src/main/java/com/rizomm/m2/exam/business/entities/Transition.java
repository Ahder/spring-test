package com.rizomm.m2.exam.business.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Transition {

  @Id
  private Long id;

  @ManyToOne
  private State from;

  @ManyToOne
  private State to;

  private String value;

  private String phrase;

  @OneToMany
  private List<Picture> pictures;

}
