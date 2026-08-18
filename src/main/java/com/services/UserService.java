package com.services;

import com.entity.User;
import com.exceptions.BusinessLogicException;
import com.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.repositories.jpa.UserJpaRepository;

import java.util.List;

@Service
public class UserService {

  private final UserJpaRepository userJpaRepository;

  public UserService(UserJpaRepository userJpaRepository) {
    this.userJpaRepository = userJpaRepository;
  }

  public List<User> getAllUsers() {
    return userJpaRepository.findAll();
  }

  public User getUserById(Long id) {
    return userJpaRepository.findById(id)
      .orElseThrow(() ->
        new EntityNotFoundException(
          "User with ID " + id + " was not found!"
        )
      );
  }

  public User createNewUser(User user) {

    if (userJpaRepository.findByEmail(user.getEmail()).isPresent()) {
      throw new BusinessLogicException(
        "A user with this email already exists!"
      );
    }

    return userJpaRepository.save(user);
  }
}