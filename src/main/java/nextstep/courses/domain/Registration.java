package nextstep.courses.domain;

import java.util.Objects;

public class Registration {
  private Long id;
  private final Long sessionId;
  private final Long userId;

  public Registration(final Long sessionId, final Long userId) {
    this(0L, sessionId, userId);
  }

  public Registration(final Long id, final Long sessionId, final Long userId) {
    this.id = id;
    this.sessionId = sessionId;
    this.userId = userId;
  }

  public Long sessionId() {
    return this.sessionId;
  }

  public Long userId() {
    return this.userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Registration that = (Registration) o;
    return Objects.equals(id, that.id) && sessionId.equals(that.sessionId) && userId.equals(that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sessionId, userId);
  }
}
