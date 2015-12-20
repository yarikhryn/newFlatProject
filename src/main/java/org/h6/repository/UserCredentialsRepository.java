package org.h6.repository;

import org.h6.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    UserCredentials findByUserId(long userId);
}
