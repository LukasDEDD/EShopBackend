package com.repositories;

import com.entity.User;

public interface UserRepository extends Repository<User> {
  User findByEmail(String email);
}

