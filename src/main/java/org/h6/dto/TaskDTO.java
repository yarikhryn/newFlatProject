package org.h6.dto;

import org.h6.domain.TaskItem;

import java.util.List;

public class TaskDTO {

    private String name;
    private String description;

    private long id;

    private UserDTO assigner;
    private UserDTO reporter;

    private String status;

    private List<String> possibleStatuses;

    private List<TaskItem> taskItems;
    private List<TaskItem> comments;
    private List<String> watchersUsernames;

    public List<String> getWatchersUsernames() {
        return watchersUsernames;
    }

    public void setWatchersUsernames(List<String> watchersUsernames) {
        this.watchersUsernames = watchersUsernames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getAssigner() {
        return assigner;
    }

    public void setAssigner(UserDTO assigner) {
        this.assigner = assigner;
    }

    public UserDTO getReporter() {
        return reporter;
    }

    public void setReporter(UserDTO reporter) {
        this.reporter = reporter;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }

    public List<TaskItem> getComments() {
        return comments;
    }

    public void setComments(List<TaskItem> comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPossibleStatuses() {
        return possibleStatuses;
    }

    public void setPossibleStatuses(List<String> possibleStatuses) {
        this.possibleStatuses = possibleStatuses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
