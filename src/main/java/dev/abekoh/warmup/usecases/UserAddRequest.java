package dev.abekoh.warmup.usecases;

import dev.abekoh.warmup.domain.types.Birthday;
import dev.abekoh.warmup.domain.types.Name;
import lombok.Getter;

import java.util.Objects;

@Getter
public class UserAddRequest {

  private final Name name;

  private final Birthday birthday;

  private UserAddRequest(Name name, Birthday birthday) {
    Objects.nonNull(name);
    Objects.nonNull(birthday);
    this.name = name;
    this.birthday = birthday;
  }

  public static UserAddRequest from(Name name, Birthday birthday) {
    return new UserAddRequest(name, birthday);
  }
}
