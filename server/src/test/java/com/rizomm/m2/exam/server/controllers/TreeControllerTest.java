package com.rizomm.m2.exam.server.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rizomm.m2.exam.business.entities.State;
import com.rizomm.m2.exam.business.entities.Transition;
import com.rizomm.m2.exam.business.entities.Tree;
import com.rizomm.m2.exam.business.services.TreeService;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TreeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TreeService treeService;

  @Before
  public void init() {

    State fakeState1 = State.builder().id(1L).name("sexe").phrase("Quelle sexe ?").build();
    State fakeState2 = State.builder().id(2L).name("sport").phrase("Quel sport ?").build();

    Transition man = Transition.builder().id(1L).from(fakeState1).to(fakeState2).value("man")
        .phrase("Homme").build();
    Transition girl = Transition.builder().id(2L).from(fakeState2).to(fakeState2).value("girl")
        .phrase("Femme").build();
    Transition triathlon = Transition.builder().id(1L).from(fakeState2).value("triathlon")
        .phrase("Triathlon").build();
    Transition cycle = Transition.builder().id(1L).from(fakeState2).value("cycle").phrase("Vélo")
        .build();
    Transition run = Transition.builder().id(1L).from(fakeState2).value("run")
        .phrase("Course à pied").build();

    Tree fakeTree1 = Tree.builder()
        .id(1L)
        .states(Arrays.asList(fakeState1, fakeState2))
        .transitions(Arrays.asList(man, girl, triathlon, cycle, run))
        .build();

    when(treeService.findById(1L)).thenReturn(Optional.of(fakeTree1));
  }

  @Test
  public void whenTreeExistThenReturnIt() throws Exception {
    mockMvc.perform(get("/trees/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)));
  }


}