package org.h6;

import org.h6.domain.User;
import org.h6.dto.UserDTO;
import org.h6.dto.builder.UserDTOBuilder;
import org.h6.dto.builder.impl.UserDTOBuilderImpl;
import org.h6.service.impl.TaskServiceImpl;
import org.hibernate.validator.cfg.defs.AssertTrueDef;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class FunctionalTests {

    @Test
    public void checkUserDTOBuilder() {
        User user = new User(0L, "admin@ducker.com", "ADMIN!", "ADMIN!");
        UserDTOBuilder dtoBuilder = new UserDTOBuilderImpl();
        UserDTO dto = dtoBuilder.build(user);
        Assert.assertTrue(dto.getFirstName().equals(user.getFirstName()));
        Assert.assertTrue(dto.getSecondName().equals(user.getSecondName()));
        Assert.assertTrue(dto.getUsername().equals(user.getUsername()));
    }


    @Test
    public void checkUsersEquality() {
        User user1 = new User(0L, "admin@ducker.com", "ADMIN!", "ADMIN!");
        User user2 = new User(0L, "admin@ducker.com", "ADMIN!", "ADMIN!");

        assert user1.equals(user2);
    }

    @Test
    public void checkUsersNotEquality() {
        User user1 = new User(0L, "admin@ducker.com", "ADMIN!", "ADMIN!");
        User user2 = new User(1L, "admin@ducker.com", "ADMIN!", "ADMIN!");

        assert !user1.equals(user2);
    }

    @Test
    public void checkDefaultUserDTO(){
        UserDTO user = new UserDTO();
        assert user.getId() == -1;
    }

    @Test
    public void checkFindAllByAssignerId() {
        User user1 = new User(0L, "admin@ducker.com", "ADMIN!", "ADMIN!");
        assert user1.toString().getClass() == String.class;
        assert user1.toString().length() > 0;
    }
}
