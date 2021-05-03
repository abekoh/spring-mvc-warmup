package dev.abekoh.warmup.domain.applications;

import dev.abekoh.warmup.domain.models.User;
import dev.abekoh.warmup.domain.models.UserRepository;
import dev.abekoh.warmup.usecases.UserAddRequest;
import dev.abekoh.warmup.usecases.UserAddResponse;
import dev.abekoh.warmup.usecases.UserUsecase;
import org.springframework.stereotype.Component;

@Component
public class UserInteractor implements UserUsecase {

  private final UserRepository repository;

  public UserInteractor(UserRepository repository) {
    this.repository = repository;
  }

  public UserAddResponse add(UserAddRequest request) {
    User user = User.create(request.getName(), request.getBirthday());
    repository.set(user);
    return UserAddResponse.from(user);
  }
}
