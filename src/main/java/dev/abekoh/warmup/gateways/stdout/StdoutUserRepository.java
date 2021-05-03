package dev.abekoh.warmup.gateways.stdout;

import dev.abekoh.warmup.domain.models.User;
import dev.abekoh.warmup.domain.models.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StdoutUserRepository implements UserRepository {
  @Override
  public void set(User user) {
    log.info("set {}", user);
  }
}
