package org.h6.repository;

import org.h6.domain.task.status.StatusTransition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusTransitionsRepository extends JpaRepository<StatusTransition, Integer> {

    List<StatusTransition> findAllByFromId(int fromId);
}
