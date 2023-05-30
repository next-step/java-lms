package nextstep.courses.domain.session.student;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;

public class SessionStudent {

  private Long id;
  private Long nsUserId;
  private Long sessionId;
  private boolean cancelFlag;

  private SessionStudentStatus studentStatus;

  private LocalDateTime createAt;
  private LocalDateTime updateAt;

  public SessionStudent(
      Long id, Long sessionId, Long nsUserId, SessionStudentStatus studentStatus,
      boolean cancelFlag, LocalDateTime createAt, LocalDateTime updateAt
  ) {
    this.id = id;
    this.nsUserId = nsUserId;
    this.sessionId = sessionId;
    this.cancelFlag = cancelFlag;
    this.createAt = createAt;
    this.updateAt = updateAt;
    this.studentStatus = studentStatus;
  }

  public SessionStudent(Session session, Long nsUserId) {
    this.sessionId = session.getId();
    this.nsUserId = nsUserId;
    this.cancelFlag = false;
  }

  public Long getId() {
    return id;
  }

  public boolean isNotCancelled() {
    return !cancelFlag;
  }

  public Long getNsUserId() {
    return nsUserId;
  }

  public Long getSessionId() {
    return sessionId;
  }

  public SessionStudentStatus getStudentStatus() {
    return studentStatus;
  }
}
