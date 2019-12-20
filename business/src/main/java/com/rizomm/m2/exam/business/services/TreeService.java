package com.rizomm.m2.exam.business.services;

import com.rizomm.m2.exam.business.entities.Tree;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface TreeService {

  Optional<Tree> findById(Long id);

  List<Tree> findAll();
}
