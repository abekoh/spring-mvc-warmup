package dev.abekoh.warmup.domain.models;

import dev.abekoh.warmup.domain.types.Birthday;
import dev.abekoh.warmup.domain.types.Name;
import dev.abekoh.warmup.domain.types.UserId;
import lombok.ToString;

import java.util.Objects;

@ToString
public class User {

  private final UserId userId;

  private final Name name;

  private final Birthday birthday;

  private final boolean isDummy;

  public User(UserId userId, Name name, Birthday birthday, boolean isDummy) {
    Objects.requireNonNull(userId);
    Objects.requireNonNull(name);
    Objects.requireNonNull(birthday);
    this.userId = userId;
    this.name = name;
    this.birthday = birthday;
    this.isDummy = isDummy;
  }

  public static User from(UserId userId, Name name, Birthday birthday, boolean isDummy) {
    return new User(userId, name, birthday, isDummy);
  }

  public static User create(Name name, Birthday birthday, boolean isDummy) {
    return new User(UserId.create(), name, birthday, isDummy);
  }
}
