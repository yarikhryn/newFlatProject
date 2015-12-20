package org.h6.dto;

public class TaskCreatingDTO {

    private String name;
    private String description;
    private String assigner;

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

    public String getAssigner() {
        return assigner;
    }

    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }
}
