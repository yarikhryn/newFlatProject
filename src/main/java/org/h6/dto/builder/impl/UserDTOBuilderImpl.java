package org.h6.dto.builder.impl;

import org.h6.domain.User;
import org.h6.dto.UserDTO;
import org.h6.dto.builder.UserDTOBuilder;

public class UserDTOBuilderImpl implements UserDTOBuilder {

    private UserDTO emptyUser = new UserDTO("empty", "Empty", "Empty");

    @Override
    public UserDTO build(User user) {
        if (user == null) {
            return emptyUser;
        } else {

            UserDTO userDTO = new UserDTO();

            userDTO.setFirstName(user.getFirstName());
            userDTO.setSecondName(user.getSecondName());
            userDTO.setUsername(user.getUsername());
            userDTO.setId(user.getId());

            return userDTO;
        }
    }
}
