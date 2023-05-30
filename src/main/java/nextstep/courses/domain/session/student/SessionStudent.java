package nextstep.courses.domain.session.student;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class SessionStudent {

  private Long id;
  private Long nsUserId;
  private Long sessionId;
  private boolean cancelFlag;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;

  public SessionStudent(Long id, Long sessionId, Long nsUserId, boolean cancelFlag, LocalDateTime createAt, LocalDateTime updateAt) {
    this.id = id;
    this.nsUserId = nsUserId;
    this.sessionId = sessionId;
    this.cancelFlag = cancelFlag;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public SessionStudent(Session session, Long nsUserId) {
    this.sessionId = session.getId();
    this.nsUserId = nsUserId;
    this.cancelFlag = false;
  }

  public boolean isNotCancelled() {
    return !cancelFlag;
  }

  public Long getNsUserId() {
    return nsUserId;
  }

  public boolean isUserOf(Long nsUserId) {
    return this.nsUserId.equals(nsUserId);
  }

  public Long getSessionId() {
    return sessionId;
  }
}
