package org.h6.dto.builder.impl;

import org.h6.domain.Task;
import org.h6.domain.User;
import org.h6.domain.task.Watcher;
import org.h6.domain.task.status.StatusItem;
import org.h6.domain.task.status.StatusTransition;
import org.h6.dto.TaskDTO;
import org.h6.dto.builder.TaskDTOBuilder;
import org.h6.dto.builder.UserDTOBuilder;
import org.h6.repository.CommentsRepository;
import org.h6.repository.StatusTransitionsRepository;
import org.h6.repository.WatchersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskDTOBuilderImpl implements TaskDTOBuilder {

    @Autowired
    private UserDTOBuilder userDTOBuilder;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private WatchersRepository watchersRepository;

    @Autowired
    private StatusTransitionsRepository statusTransitionsRepository;

    @Override
    public TaskDTO build(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        if (task != null) {
            taskDTO.setAssigner(userDTOBuilder.build(task.getAssigner()));
            taskDTO.setReporter(userDTOBuilder.build(task.getReporter()));
            taskDTO.setName(task.getName());
            taskDTO.setDescription(task.getDescription());

            taskDTO.setId(task.getId());

            taskDTO.setComments(new ArrayList<>(commentsRepository.findAllByTaskId(task.getId())));

            taskDTO.setStatus(task.getStatus().getName());

            taskDTO.setPossibleStatuses(statusTransitionsRepository
                    .findAllByFromId(task.getStatus().getId())
                    .stream()
                    .map(StatusTransition::getTo).map(StatusItem::getName)
                    .collect(Collectors.toList()));

            taskDTO.setWatchersUsernames(watchersRepository.findAllByTaskId(task.getId())
                    .stream()
                    .map(Watcher::getUser).map(User::getUsername)
                    .collect(Collectors.toList()));
        }
        return taskDTO;
    }
}
