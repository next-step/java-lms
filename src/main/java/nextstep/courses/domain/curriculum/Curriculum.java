package nextstep.courses.domain.curriculum;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.domain.base.BaseInfo;

public class Curriculum {

  private Long id;

  private Long batchId;

  private Long sessionId;

  private BaseInfo baseInfo;

  public Curriculum(Long batchId, Long sessionId, Long creatorId) {
    this(null, batchId, sessionId, creatorId);
  }

  public Curriculum(Long id, Long batchId, Long sessionId, Long creatorId) {
    this(id, batchId, sessionId, creatorId, LocalDateTime.now(), LocalDateTime.now());
  }

  public Curriculum(Long id, Long batchId, Long sessionId
      , Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(id, batchId, sessionId
        , new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Curriculum(Long id, Long batchId, Long sessionId,
      BaseInfo baseInfo) {
    this.id = id;
    this.batchId = batchId;
    this.sessionId = sessionId;
    this.baseInfo = baseInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Curriculum that = (Curriculum) o;
    return Objects.equals(id, that.id) && Objects.equals(batchId, that.batchId)
        && Objects.equals(sessionId, that.sessionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, batchId, sessionId);
  }

  public Long getId() {
    return id;
  }

  public Long getBatchId() {
    return batchId;
  }

  public Long getSessionId() {
    return sessionId;
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }
}
