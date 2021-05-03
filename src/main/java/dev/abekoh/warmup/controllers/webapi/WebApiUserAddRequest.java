package dev.abekoh.warmup.controllers.webapi;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class WebApiUserAddRequest {

  private final String firstName;

  private final String lastName;

  private final Integer birthYear;

  private final Integer birthMonth;

  private final Integer birthDate;

  private final Boolean isDummy;

  public Boolean getIsDummy() {
    if (isDummy == null) {
      return false;
    }
    return isDummy;
  }
}
