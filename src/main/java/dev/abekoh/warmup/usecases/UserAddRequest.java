package dev.abekoh.warmup.usecases;

import dev.abekoh.warmup.domain.types.Birthday;
import dev.abekoh.warmup.domain.types.Name;
import lombok.Getter;

import java.util.Objects;

@Getter
public class UserAddRequest {

  private final Name name;

  private final Birthday birthday;

  private final boolean isDummy;

  private UserAddRequest(Name name, Birthday birthday, boolean isDummy) {
    Objects.nonNull(name);
    Objects.nonNull(birthday);
    this.name = name;
    this.birthday = birthday;
    this.isDummy = isDummy;
  }

  public static UserAddRequest from(Name name, Birthday birthday) {
    return new UserAddRequest(name, birthday, false);
  }

  public static UserAddRequest from(Name name, Birthday birthday, boolean isDummy) {
    return new UserAddRequest(name, birthday, isDummy);
  }
}
