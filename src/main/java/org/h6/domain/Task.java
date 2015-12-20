package org.h6.domain;

import org.h6.domain.task.status.StatusItem;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "assigner_id", nullable = false)
    private User assigner;


    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private StatusItem status;


    public Task() {
    }

    public Task(String name, String description, User reporter, User assigner) {
        this.name = name;
        this.description = description;
        this.reporter = reporter;
        this.assigner = assigner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (!name.equals(task.name)) return false;
        if (!description.equals(task.description)) return false;
        if (!reporter.equals(task.reporter)) return false;
        if (!assigner.equals(task.assigner)) return false;
        return status.equals(task.status);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + reporter.hashCode();
        result = 31 * result + assigner.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
