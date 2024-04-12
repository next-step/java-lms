package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {

  protected Long id;

  protected LocalDateTime createdAt = LocalDateTime.now();

  protected LocalDateTime updatedAt;

  public BaseEntity(Long id) {
    this.id = id;
  }

  public BaseEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public BaseEntity() {
  }

  public Long getId(){
    return id;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }
}
