package dev.abekoh.warmup.domain.types;

import lombok.ToString;

import java.util.Objects;

@ToString
public class Name {

  private final String firstName;

  private final String lastName;

  public Name(String firstName, String lastName) {
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);
    this.firstName = firstName;
    this.lastName = lastName;
  }

  private static Name from(String firstName, String lastName) {
    return new Name(firstName, lastName);
  }
}
