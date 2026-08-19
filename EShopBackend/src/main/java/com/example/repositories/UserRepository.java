package com.example.repositories;

import com.example.entity.User;

public interface UserRepository extends Repository<User> {
  User findByEmail(String email);
}

