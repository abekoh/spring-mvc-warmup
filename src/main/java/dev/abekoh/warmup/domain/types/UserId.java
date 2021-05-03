package dev.abekoh.warmup.domain.types;

import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@ToString
public class UserId {

  private final String id;

  private UserId(String id) {
    Objects.requireNonNull(id);
    this.id = id;
  }

  public static UserId create() {
    return new UserId(UUID.randomUUID().toString());
  }
}
