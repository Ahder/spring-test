package com.rizomm.m2.exam.business.converters;

import com.rizomm.m2.exam.business.dto.TreeDto;
import com.rizomm.m2.exam.business.entities.Tree;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TreeConverter {

  private ModelMapper modelMapper;

  public TreeConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public TreeDto convert(Tree tree) {
    return modelMapper.map(tree, TreeDto.class);
  }

  public Tree convert(TreeDto treeDto) {
    return modelMapper.map(treeDto, Tree.class);
  }
}
