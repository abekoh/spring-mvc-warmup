package dev.abekoh.warmup.usecases;

import dev.abekoh.warmup.domain.models.User;

import java.util.Objects;

public class UserAddResponse {
  private final User user;

  private UserAddResponse(User user) {
    Objects.requireNonNull(user);
    this.user = user;
  }

  public static UserAddResponse from(User user) {
    return new UserAddResponse(user);
  }
}
