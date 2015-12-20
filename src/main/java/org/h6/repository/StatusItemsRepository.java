package org.h6.repository;

import org.h6.domain.task.status.StatusItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusItemsRepository extends JpaRepository<StatusItem, Integer> {
    StatusItem findByName(String name);
}
