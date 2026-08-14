package services;

import model.User;
import repositories.UserRepository;

import java.util.List;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(long id) {
    return userRepository.findById(id);
  }

  public void createNewUser(User user) {

    if (userRepository.findByEmail(user.getEmail()) != null) {
      throw new RuntimeException("A user with this email already exists!");
    }
    List<User> allUsers = userRepository.findAll();
    long nextId = allUsers.stream()
      .mapToLong(User::getId)
      .max()
      .orElse(0) + 1;

    user.setId(nextId);

    userRepository.save(user);
  }
}
