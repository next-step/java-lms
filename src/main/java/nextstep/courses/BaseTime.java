package nextstep.courses;

import java.time.LocalDateTime;

public class BaseTime {

  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public BaseTime(LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public BaseTime() {
    this(LocalDateTime.now(), null);
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
