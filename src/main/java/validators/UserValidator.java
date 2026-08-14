package validators;

import model.User;

public class UserValidator {

  public static void validate(User user) {
    if (user.getEmail() == null || !user.getEmail().contains("@")) {
      throw new IllegalArgumentException("Invalid email");
    }

    if (user.getPassword() == null || user.getPassword().isEmpty()) {
      throw new IllegalArgumentException("Password required");
    }
  }
}
