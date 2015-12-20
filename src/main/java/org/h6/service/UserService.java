package org.h6.service;

import org.h6.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    User findById(long id);

    User findByUsername(String username);

    void create(User user, String password);

    List<User> findAll();
}
