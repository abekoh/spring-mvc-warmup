package dev.abekoh.warmup.usecases;

import dev.abekoh.warmup.domain.models.User;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@Getter
public class UserAddResponse {
  private final User user;

  public UserAddResponse(User user) {
    Objects.requireNonNull(user);
    this.user = user;
  }

  public static UserAddResponse from(User user) {
    return new UserAddResponse(user);
  }
}
