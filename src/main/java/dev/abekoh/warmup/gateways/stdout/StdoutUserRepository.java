package dev.abekoh.warmup.gateways.stdout;

import dev.abekoh.warmup.domain.models.User;
import dev.abekoh.warmup.domain.models.UserRepository;

public class StdoutUserRepository implements UserRepository {
  @Override
  public void set(User user) {}
}
