package nextstep.courses.domain.session;

import java.util.Objects;

public class Term {
  private static final int MIN_TERM = 1;

  private final int value;

  public Term(int value) {
    validate(value);
    this.value = value;
  }

  private void validate(int value) {
    if (value < MIN_TERM) {
      throw new IllegalArgumentException("기수는 " + MIN_TERM + " 이상이어야 합니다.");
    }
  }

  public int getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Term term = (Term) o;
    return value == term.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}