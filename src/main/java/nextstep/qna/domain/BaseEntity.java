package nextstep.qna.domain;

import java.time.LocalDateTime;

public class BaseEntity {

  protected Long id;

  private final LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  protected boolean deleted = false;

  public BaseEntity() {
  }

  public Long getId() {
    return id;
  }

  public boolean isDeleted() {
    return deleted;
  }

}
