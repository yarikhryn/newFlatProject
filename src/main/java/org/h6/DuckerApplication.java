package org.h6;

import org.h6.service.UsersNotifier;
import org.h6.service.impl.EmptyUserNotifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DuckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuckerApplication.class, args);
    }

    @Bean
    UsersNotifier usersNotifier() {
        return new EmptyUserNotifier();
    }
}
