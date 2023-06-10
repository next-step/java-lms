package nextstep.courses.domain.base;

import java.time.LocalDateTime;

public class BaseInfo {

  private Long creatorId;

  private LocalDateTime createdAt = LocalDateTime.now();

  private LocalDateTime updatedAt;

  public BaseInfo(Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.creatorId = creatorId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
