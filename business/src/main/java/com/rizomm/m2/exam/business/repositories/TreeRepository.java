package com.rizomm.m2.exam.business.repositories;

import com.rizomm.m2.exam.business.entities.Tree;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {

  Optional<Tree> findById(Long id);

}
