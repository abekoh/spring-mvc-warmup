package dev.abekoh.warmup.controllers.webapi;

import dev.abekoh.warmup.domain.types.Birthday;
import dev.abekoh.warmup.domain.types.Name;
import dev.abekoh.warmup.usecases.UserAddRequest;
import dev.abekoh.warmup.usecases.UserUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/users")
public class WebApiController {

  private final UserUsecase usecase;

  public WebApiController(UserUsecase usecase) {
    this.usecase = usecase;
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebApiUserAddResponse> add(@RequestBody WebApiUserAddRequest request) {
    log.debug("receive request: {}", request);
    try {
      var usecaseResponse =
          usecase.add(
              UserAddRequest.from(
                  Name.from(request.getFirstName(), request.getLastName()),
                  Birthday.from(
                      request.getBirthYear(), request.getBirthMonth(), request.getBirthDate())));
      return ResponseEntity.ok(WebApiUserAddResponse.from(usecaseResponse));
    } catch (NullPointerException exception) {
      return ResponseEntity.badRequest()
          .body(WebApiUserAddResponse.failedBecause("some arguments are invalid."));
    }
  }
}
