package org.h6.service.impl;

import org.h6.domain.Task;
import org.h6.service.UsersNotifier;

import java.util.logging.Logger;

public class EmptyUserNotifier implements UsersNotifier {

    @Override
    public void notifyAll(Task task, Context data) {
        Logger.getLogger("UserNotifier").info(task.getName() + " " + task.getAssigner().getUsername() + " " + task.getReporter().getUsername() + ", about : " + data.toString());
    }
}
