package org.h6.service;

import org.h6.domain.Task;

import java.util.HashMap;

public interface UsersNotifier {

    class Context extends HashMap<String, Object> {
    }

    // TODO switch Object to smth else
    void notifyAll(Task task, Context data);
}
