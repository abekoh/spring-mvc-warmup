package dev.abekoh.warmup.domain.types;

import java.time.LocalDate;
import java.util.Objects;

public class Birthday {

  private final LocalDate date;

  private Birthday(LocalDate date) {
    Objects.requireNonNull(date);
    this.date = date;
  }

  public static Birthday from(LocalDate date) {
    return new Birthday(date);
  }
}
