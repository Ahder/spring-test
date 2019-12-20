package com.rizomm.m2.exam.business.services.impl;

import com.rizomm.m2.exam.business.entities.Tree;
import com.rizomm.m2.exam.business.repositories.TreeRepository;
import com.rizomm.m2.exam.business.services.TreeService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl implements TreeService {

  private TreeRepository treeRepository;

  @Override
  public Optional<Tree> findById(Long id) {
    return treeRepository.findById(id);
  }

  @Override
  public List<Tree> findAll() {
    return treeRepository.findAll();
  }
}
