package org.h6.web;

import org.h6.domain.Task;
import org.h6.domain.task.Watcher;
import org.h6.domain.task.status.StatusItem;
import org.h6.dto.TaskCreatingDTO;
import org.h6.dto.TaskDTO;
import org.h6.dto.builder.TaskDTOBuilder;
import org.h6.repository.StatusItemsRepository;
import org.h6.repository.WatchersRepository;
import org.h6.service.TaskService;
import org.h6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private WatchersRepository watchersRepository;

    @Autowired
    private TaskDTOBuilder dtoBuilder;

    @Autowired
    private StatusItemsRepository statusItemsRepository;


    @RequestMapping(value = "api/tasks/{id}", method = RequestMethod.GET)
    public TaskDTO findTask(@PathVariable("id") long id) {
        return dtoBuilder.build(taskService.findOne(id));
    }

    @RequestMapping(value = "api/tasks/all", method = RequestMethod.GET)
    public List<TaskDTO> finAll() {
        return taskService.findAll().stream().map(dtoBuilder::build).collect(Collectors.toList());
    }

    @RequestMapping(value = "api/tasks/search", method = RequestMethod.GET)
    public List<TaskDTO> search(
            @RequestParam(value = "user_id") long userId
            //@RequestParam(value = "status", defaultValue = "OPEN") String status
    ) {
        return taskService.findAllByAssignerId(userId).stream()
                //.filter(t -> t.getStatus().getName().equals(status))
                .map(dtoBuilder::build)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "api/tasks/create", method = RequestMethod.POST)
    public ResponseEntity<Long> createNewTask(
            @RequestBody TaskCreatingDTO dto
    ) {
        org.h6.domain.User assigner = userService.findByUsername(dto.getAssigner());
        if (assigner != null) {
            org.h6.domain.User reporter = getLoggedUser();
            Task task = new Task(dto.getName(), dto.getDescription(), assigner, reporter);
            task.setStatus(statusItemsRepository.getOne(1));
            Task createdTask = taskService.create(task);
            return new ResponseEntity<>(createdTask.getId(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // TODO move watchers methods to another controller
    // TODO switch method to POST, and remove return value, now use for test
    @RequestMapping(value = "api/tasks/{id}/start_watch", method = RequestMethod.GET)
    public TaskDTO startWatchTasK(
            @PathVariable("id") long taskId) {

        Task task = taskService.findOne(taskId);

        // todo make one code to get current logged user
        org.h6.domain.User user = getLoggedUser();

        if (!task.getAssigner().getId().equals(user.getId()) && !task.getReporter().getId().equals(user.getId())) {
            Watcher watcher = new Watcher();
            watcher.setTaskId(taskId);
            watcher.setUser(user);
            watchersRepository.saveAndFlush(watcher);
        }

        return findTask(taskId);
    }

    private org.h6.domain.User getLoggedUser() {
        return userService.findByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }

    @RequestMapping(value = "api/tasks/{id}/stop_watch", method = RequestMethod.GET)
    public TaskDTO stopWatchTask(@PathVariable("id") long taskId) {
        // todo make one code to get current logged user
        org.h6.domain.User user = getLoggedUser();


        // TODO make specified query instead filtering over all watchers
        watchersRepository.findAllByTaskId(taskId)
                .stream()
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .findAny()
                .ifPresent(watchersRepository::delete);


        return findTask(taskId);
    }

    @RequestMapping(value = "api/tasks/{id}/update_status", method = RequestMethod.POST)
    public TaskDTO updateTaskStatus(
            @PathVariable("id") long taskId,
            @RequestBody StatusItem statusItem) {
        Task task = taskService.findOne(taskId);
        task.setStatus(statusItemsRepository.findByName(statusItem.getName()));
        return dtoBuilder.build(taskService.update(task));
    }

}
