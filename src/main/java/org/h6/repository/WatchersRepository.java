package org.h6.repository;


import org.h6.domain.task.Watcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchersRepository extends JpaRepository<Watcher, Long> {

    List<Watcher> findAllByTaskId(long taskId);
}
