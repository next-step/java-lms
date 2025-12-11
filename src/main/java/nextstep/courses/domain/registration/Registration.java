package nextstep.courses.domain.registration;

import java.time.LocalDateTime;
import java.util.Objects;

public class Registration {
  private final Long id;
  private final Long sessionId;
  private final Long studentId;
  private final LocalDateTime enrolledAt;
  private boolean approved;

  public Registration(Long sessionId, Long studentId) {
    this(null, sessionId, studentId, LocalDateTime.now(), true);
  }

  public Registration(Long id, Long sessionId, Long studentId, LocalDateTime enrolledAt) {
    this(id, sessionId, studentId, enrolledAt, true);
  }

  public Registration(Long id, Long sessionId, Long studentId, LocalDateTime enrolledAt, boolean approved) {
    this.id = id;
    this.sessionId = sessionId;
    this.studentId = studentId;
    this.enrolledAt = enrolledAt;
    this.approved = approved;
  }

  public boolean contains(Long studentId) {
    return Objects.equals(this.studentId, studentId);
  }

  public Long getId() {
    return id;
  }

  public Long getSessionId() {
    return sessionId;
  }

  public Long getStudentId() {
    return studentId;
  }

  public LocalDateTime getEnrolledAt() {
    return enrolledAt;
  }

  public void approve() {
    this.approved = true;
  }

  public void unregister() {
    if(!this.approved){
      throw new IllegalArgumentException("선발되어 취소할 수 없습니다.");
    }
  }
}