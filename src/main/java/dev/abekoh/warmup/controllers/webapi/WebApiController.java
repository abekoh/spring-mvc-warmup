package dev.abekoh.warmup.controllers.webapi;

import dev.abekoh.warmup.domain.types.Birthday;
import dev.abekoh.warmup.domain.types.Name;
import dev.abekoh.warmup.usecases.UserAddRequest;
import dev.abekoh.warmup.usecases.UserUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
  public WebApiUserAddResponse add(WebApiUserAddRequest request) {
    var usecaseResponse =
        usecase.add(
            UserAddRequest.from(
                Name.from(request.getFirstName(), request.getLastName()),
                Birthday.from(
                    request.getBirthYear(), request.getBirthMonth(), request.getBirthDate())));
    return WebApiUserAddResponse.from(usecaseResponse);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({NullPointerException.class})
  public WebApiUserAddResponse handleError() {
    return WebApiUserAddResponse.failedBecause("some required arguments are not found.");
  }
}
