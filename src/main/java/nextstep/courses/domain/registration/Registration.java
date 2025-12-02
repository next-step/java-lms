package nextstep.courses.domain.registration;

import java.time.LocalDateTime;

public class Registration {
  private final Long sessionId;
  private final Long studentId;
  private final LocalDateTime enrolledAt;

  public Registration(Long sessionId, Long studentId) {
    this(sessionId, studentId, LocalDateTime.now());
  }

  public Registration(Long sessionId, Long studentId, LocalDateTime enrolledAt) {
    this.sessionId = sessionId;
    this.studentId = studentId;
    this.enrolledAt = enrolledAt;
  }
  public boolean contains(Long studentId) {
    return this.studentId == studentId;
  }

}