package org.h6.service.impl;

import org.h6.domain.User;
import org.h6.domain.UserCredentials;
import org.h6.repository.UserCredentialsRepository;
import org.h6.repository.UserRepository;
import org.h6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository credentialsRepository;

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void create(User user, String password) {
        credentialsRepository.saveAndFlush(new UserCredentials(0L, userRepository.saveAndFlush(user), password));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(findByUsername(username))
                .map(user -> credentialsRepository.findByUserId(user.getId()))
                .map(UserServiceImpl::createUser)
                .orElseThrow(() -> new UsernameNotFoundException("Username : " + username));
    }


    private static org.springframework.security.core.userdetails.User createUser(UserCredentials credentials) {
        return new org.springframework.security.core.userdetails.User(credentials.getUser().getUsername(), credentials.getPassword(), Collections.emptyList());
    }
}
