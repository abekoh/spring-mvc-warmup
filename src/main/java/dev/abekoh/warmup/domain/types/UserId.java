package dev.abekoh.warmup.domain.types;

import java.util.Objects;
import java.util.UUID;

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
