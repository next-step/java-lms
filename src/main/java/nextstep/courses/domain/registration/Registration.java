package nextstep.courses.domain.registration;

import java.time.LocalDateTime;
import java.util.Objects;

public class Registration {
  private final Long id;
  private final Long sessionId;
  private final Long studentId;
  private final LocalDateTime enrolledAt;

  public Registration(Long sessionId, Long studentId) {
    this(null, sessionId, studentId, LocalDateTime.now());
  }

  public Registration(Long id, Long sessionId, Long studentId, LocalDateTime enrolledAt) {
    this.id = id;
    this.sessionId = sessionId;
    this.studentId = studentId;
    this.enrolledAt = enrolledAt;
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
}