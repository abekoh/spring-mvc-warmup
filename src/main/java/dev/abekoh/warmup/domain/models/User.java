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

  private User(UserId userId, Name name, Birthday birthday) {
    Objects.nonNull(userId);
    Objects.nonNull(name);
    Objects.nonNull(birthday);
    this.userId = userId;
    this.name = name;
    this.birthday = birthday;
  }

  public static User from(UserId userId, Name name, Birthday birthday) {
    return new User(userId, name, birthday);
  }

  public static User create(Name name, Birthday birthday) {
    return new User(UserId.create(), name, birthday);
  }
}
