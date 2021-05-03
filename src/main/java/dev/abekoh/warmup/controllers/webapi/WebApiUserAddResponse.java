package dev.abekoh.warmup.controllers.webapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.abekoh.warmup.usecases.UserAddResponse;
import lombok.Data;

import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebApiUserAddResponse {

  private final boolean isSucceeded;

  private final UserAddResponse userAddResponse;

  private final String errorMessage;

  private WebApiUserAddResponse(
      boolean isSucceeded, UserAddResponse userAddResponse, String errorMessage) {
    this.isSucceeded = isSucceeded;
    this.userAddResponse = userAddResponse;
    this.errorMessage = errorMessage;
  }

  public static WebApiUserAddResponse from(UserAddResponse userAddResponse) {
    Objects.requireNonNull(userAddResponse);
    return new WebApiUserAddResponse(true, userAddResponse, null);
  }

  public static WebApiUserAddResponse failedBecause(String errorMessage) {
    Objects.requireNonNull(errorMessage);
    return new WebApiUserAddResponse(false, null, errorMessage);
  }
}
