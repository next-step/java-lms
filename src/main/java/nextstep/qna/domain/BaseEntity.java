package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity that = (BaseEntity) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
