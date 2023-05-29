package nextstep.courses.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.BaseTimeEntity;


public class SessionEnrollmentEntity extends BaseTimeEntity {

  private Long id;

  private Long sessionId;

  private Long userId;

  public SessionEnrollmentEntity() {
  }

  public SessionEnrollmentEntity(Long id, Long sessionId, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.sessionId = sessionId;
    this.userId = userId;
  }

  public SessionEnrollmentEntity(Long sessionId, Long userId) {
    this(null, sessionId, userId, LocalDateTime.now(), LocalDateTime.now());
  }


  public Long getSessionId() {
    return sessionId;
  }

  public Long getUserId() {
    return userId;
  }

  public LocalDateTime getCreatedAt(){
    return createdAt;
  }

  public LocalDateTime getUpdatedAt(){
    return updatedAt;
  }
}
