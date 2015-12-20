package org.h6.domain.task;

import org.h6.domain.TaskItem;
import org.h6.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "watchers")
public class Watcher implements TaskItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "task_id")
    private long taskId;


    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public long getTaskId() {
        return taskId;
    }
}
