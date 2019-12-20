package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.converters.TreeConverter;
import com.rizomm.m2.exam.business.entities.Tree;
import com.rizomm.m2.exam.business.services.TreeService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trees")
public class TreeController {

  private TreeService treeService;
  private TreeConverter treeConverter;

  public TreeController(
      TreeService treeService,
      TreeConverter treeConverter) {
    this.treeService = treeService;
    this.treeConverter = treeConverter;
  }

  @GetMapping
  public ResponseEntity<List<Tree>> getAllTrees() {
    return ResponseEntity.ok(treeService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity getTreeById(@PathVariable Long id) {
    Optional<Tree> byId = treeService.findById(id);

    return byId.map(tree -> ResponseEntity.ok(treeConverter.convert(tree))).orElse(ResponseEntity.notFound().build());

  }
}
