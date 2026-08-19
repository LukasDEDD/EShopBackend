package com.example.repositories.legacy;


import com.google.gson.reflect.TypeToken;
import com.example.entity.User;
import com.example.repositories.UserRepository;

import java.util.List;

public class FileUserRepository extends AbstractFileRepository<User> implements UserRepository {

  public FileUserRepository(String filePath) {
    super(filePath, new TypeToken<List<User>>(){}.getType());
  }

  @Override
  protected Long getEntityId(User entity) { return entity.getId(); }

  @Override
  public User findById(long id) {
    return loadAll().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
  }

  @Override
  public User findByEmail(String email) {
    return loadAll().stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
  }
}
