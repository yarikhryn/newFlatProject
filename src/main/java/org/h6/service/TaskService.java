package org.h6.service;

import org.h6.domain.Task;

import java.util.List;

public interface TaskService{

    Task findOne(long id);

    List<Task> findAll();

    List<Task> findAllByAssignerId(long assignerId);

    Task create(Task task);

    Task update(Task task);

}
