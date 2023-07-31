package nextstep.courses.domain.base;

import java.time.LocalDateTime;

public class BaseInfo {

  private final Long creatorId;

  private final LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public BaseInfo(Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.creatorId = creatorId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getCreatorId() {
    return creatorId;
  }
}
