package nextstep.qna.domain;

import java.time.LocalDateTime;

public class BaseEntity {

  protected Long id;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  protected boolean deleted = false;

  public BaseEntity(Long id) {
    this.id = id;
  }

  public BaseEntity() {
  }

  public Long getId() {
    return id;
  }

  public boolean isDeleted() {
    return deleted;
  }

}
