package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

public class Student {
  private Long sessionUserId;

  private Long sessionId;

  private Long nsUserId;

  public Student(Session session, NsUser user) {
    this(0L, session.getId(), user.getId());
  }

  public Student(Long sessionUserId, Long sessionId, Long nsUserId) {
    this.sessionUserId = sessionUserId;
    this.sessionId = sessionId;
    this.nsUserId = nsUserId;
  }

  public boolean isTaking(Session session, NsUser nsUser) {
    return this.sessionId.equals(session.getId()) && this.nsUserId.equals(nsUser.getId());
  }

  public Long getSessionUserId() {
    return sessionUserId;
  }

  public Long getSessionId() {
    return this.sessionId;
  }

  public Long getNsUserId() {
    return this.nsUserId;
  }

  @Override
  public String toString() {
    return "Student{" +
        "sessionUserId=" + sessionUserId +
        ", sessionId=" + sessionId +
        ", nsUserId=" + nsUserId +
        '}';
  }
}
