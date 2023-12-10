package nextstep.courses;

import java.time.LocalDateTime;

public class Time {

  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public Time() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = null;
  }
  public Time(LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }


  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
