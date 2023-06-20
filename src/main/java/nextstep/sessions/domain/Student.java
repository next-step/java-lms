package nextstep.sessions.domain;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

public class Student {
  private Long id;

  private Long sessionId;

  private Long nsUserId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public Student(Session session, NsUser user, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(0L, session.getId(), user.getId(), createdAt, updatedAt);
  }

  public Student(Long id, Long sessionId, Long nsUserId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.sessionId = sessionId;
    this.nsUserId = nsUserId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public boolean isTaking(Session session, NsUser nsUser) {
    return this.sessionId.equals(session.getId()) && this.nsUserId.equals(nsUser.getId());
  }

  public boolean isTaking(Student student) {
    return this.sessionId.equals(student.getSessionId()) && this.nsUserId.equals(student.getNsUserId());
  }

  public Long getId() {
    return id;
  }

  public Long getSessionId() {
    return this.sessionId;
  }

  public Long getNsUserId() {
    return this.nsUserId;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", sessionId=" + sessionId +
        ", nsUserId=" + nsUserId +
        '}';
  }
}
