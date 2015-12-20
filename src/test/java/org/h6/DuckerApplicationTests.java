package org.h6;

import org.h6.domain.Task;
import org.h6.domain.User;
import org.h6.service.TaskService;
import org.h6.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@SpringApplicationConfiguration(classes = {DuckerApplication.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DuckerApplicationTests {

    @Autowired
    private UserService users;

    @Autowired
    private TaskService tasks;

    @Test
    public void checkUserService() {
        User user = users.findByUsername("admin@ducker.com");

        assert user != null;

        Assert.assertTrue(user.getFirstName().equals("Admin"));
        Assert.assertTrue(user.getSecondName().equals("ADMIN"));
    }

    @Test
    public void checkTaskService() {
        List<Task> allTasks = tasks.findAll();
        assert allTasks.size() > 0;
    }

    @Test
    public void checkUserService2() {
        List<User> allUsers = users.findAll();
        assert !(allUsers.size() == 0);
    }

    @Test
    public void checkTaskName() {
        List<Task> allTasks = tasks.findAll();
        assert allTasks.stream().map(Task::getReporter).map(User::getUsername).filter(a -> a.equals("admin@ducker.com")).findAny().isPresent();
    }
}
