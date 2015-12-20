package org.h6.service.impl;


import org.h6.domain.Task;
import org.h6.repository.TaskReposirtory;
import org.h6.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskReposirtory reposirtory;


    @Override
    public Task findOne(long id) {
        return reposirtory.findOne(id);
    }

    @Override
    public List<Task> findAll() {
        return reposirtory.findAll();
    }

    @Override
    public List<Task> findAllByAssignerId(long assignerId) {
        return reposirtory.findAllByAssignerId(assignerId);
    }

    @Override
    public Task create(Task task) {
        return reposirtory.saveAndFlush(task);
    }

    @Override
    public Task update(Task task) {
        return reposirtory.saveAndFlush(task);
    }
}
