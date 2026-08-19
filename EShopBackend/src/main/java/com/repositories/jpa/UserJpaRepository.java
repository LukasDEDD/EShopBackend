package com.repositories.jpa;

import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
