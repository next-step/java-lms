package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

public class SessionStudent {

  private Long id;
  private Long nsUserId;
  private Long sessionId;
  private boolean cancelFlag;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;

  private NsUser nsUser;
  private Session session;

  public SessionStudent(SessionStudent student) {
    this.id = student.id;
    this.nsUserId = student.nsUserId;
    this.sessionId = student.sessionId;
    this.cancelFlag = student.cancelFlag;
    this.createAt = student.createAt;
    this.updateAt = student.updateAt;
    this.nsUser = student.nsUser;
    this.session = student.session;
  }

  public SessionStudent(Long id, Long sessionId, Long nsUserId, boolean cancelFlag, LocalDateTime createAt, LocalDateTime updateAt) {
    this.id = id;
    this.nsUserId = nsUserId;
    this.sessionId = sessionId;
    this.cancelFlag = cancelFlag;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public SessionStudent(Session session, NsUser nsUser) {
    this.session = session;
    this.sessionId = session.getId();
    this.nsUser = nsUser;
    this.nsUserId = nsUser.getId();
  }

  public SessionStudent(SessionStudent student, NsUser nsUser) {
    this(student);
    this.nsUser = nsUser;
    this.nsUserId = nsUser.getId();
  }

  public boolean isNotCancelled() {
    return !cancelFlag;
  }

  public Long getNsUserId() {
    return nsUserId;
  }

  public boolean isUserOf(NsUser nsUser) {
    return this.nsUser.matchUser(nsUser);
  }

  public boolean isUserOf(Long nsUserId) {
    return this.nsUserId.equals(nsUser.getId());
  }

  public NsUser getNsUser() {
    return nsUser;
  }

  public Long getSessionId() {
    return sessionId;
  }
}
