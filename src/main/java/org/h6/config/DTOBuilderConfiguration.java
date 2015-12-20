package org.h6.config;

import org.h6.dto.builder.TaskDTOBuilder;
import org.h6.dto.builder.UserDTOBuilder;
import org.h6.dto.builder.impl.TaskDTOBuilderImpl;
import org.h6.dto.builder.impl.UserDTOBuilderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOBuilderConfiguration {

    @Bean
    UserDTOBuilder userDTOBuilder(){
        return new UserDTOBuilderImpl();
    }

    @Bean
    TaskDTOBuilder taskDTOBuilder(){
        return new TaskDTOBuilderImpl();
    }
}
