package nextstep.courses.domain.session.teacher;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class SessionTeacher {

  private Long id;
  private Long sessionId;
  private Long nsUserId;
  private boolean isActive;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;

  public SessionTeacher(
      Long id, Long sessionId, Long nsUserId, boolean isActive,
      LocalDateTime createAt, LocalDateTime updateAt
  ) {
    this.id = id;
    this.sessionId = sessionId;
    this.nsUserId = nsUserId;
    this.isActive = isActive;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public SessionTeacher(Session session, NsUser user) {
    this.sessionId = session.getId();
    this.nsUserId = user.getId();
  }

  public SessionTeacher(long id, Session session, NsUser user) {
    this(session, user);
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public Long getNsUserId() {
    return nsUserId;
  }

  public boolean isActive() {
    return isActive;
  }

  public boolean isNotActive() {
    return !isActive;
  }

}
