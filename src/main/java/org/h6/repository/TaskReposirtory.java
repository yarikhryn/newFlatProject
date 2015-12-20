package org.h6.repository;

import org.h6.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskReposirtory extends JpaRepository<Task, Long> {
    List<Task> findAllByAssignerId(long assignerId);
}
