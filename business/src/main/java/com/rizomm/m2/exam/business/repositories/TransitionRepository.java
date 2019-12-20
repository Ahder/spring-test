package com.rizomm.m2.exam.business.repositories;

import com.rizomm.m2.exam.business.entities.Transition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitionRepository extends JpaRepository<Transition, Long> {

}
