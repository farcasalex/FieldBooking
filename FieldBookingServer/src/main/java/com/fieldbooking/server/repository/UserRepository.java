package com.fieldbooking.server.repository;

import com.fieldbooking.server.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);

    User findByUsernameEquals(String username);
}
