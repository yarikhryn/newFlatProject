package org.h6.web;

import org.h6.dto.UserDTO;
import org.h6.dto.builder.UserDTOBuilder;
import org.h6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    @Autowired
    private UserService service;

    @Autowired
    private UserDTOBuilder dtoBuilder;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    UserDTO findUser(@PathVariable("id") long id) {
        return dtoBuilder.build(service.findById(id));
    }

    @RequestMapping(value = "api/users/find", method = RequestMethod.GET)
    UserDTO findUser(@RequestParam("username") String username) {
        return dtoBuilder.build(service.findByUsername(username));
    }

    @RequestMapping(value = "api/users/search", method = RequestMethod.GET)
    List<UserDTO> searchUsers(@RequestParam("query") String query) {
        return service.findAll().stream()
                .filter(user -> user.getUsername().startsWith(query) || user.getFirstName().startsWith(query))
                .map(dtoBuilder::build)
                .collect(Collectors.toList());
    }
}
