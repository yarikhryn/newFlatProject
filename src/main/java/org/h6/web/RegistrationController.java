package org.h6.web;

import org.h6.domain.User;
import org.h6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    UserService service;

    @Autowired
    public void createAdminUser() {
        if (service.findByUsername("admin@ducker.com") == null) {
            createNewUser("admin@ducker", "admin", "ADMIN", "ADMIN");
        }
    }


    @RequestMapping(value = "api/users/create", method = RequestMethod.POST)
    public void createNewUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("secondName") String secondName) {
        service.create(new User(null, username, firstName, secondName), password);
    }
}
