package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;

public class Registration {

  private Long id;

  private NsUser nsUser;

  private Session session;

  private LocalDateTime registeredAt;

  private boolean canceled = false;

  public Registration(NsUser nsUser, Session session) {
    this(null, nsUser, session, LocalDateTime.now());
  }

  public Registration(Long id, NsUser nsUser, Session session, LocalDateTime registeredAt) {
    this.id = id;
    validateNsUser(nsUser);
    this.nsUser = nsUser;
    validateSession(session);
    this.session = session;
    this.registeredAt = registeredAt;
  }

  private void validateNsUser(NsUser nsUser) {
    if (nsUser == null) {
      throw new NotFoundException();
    }
  }

  private void validateSession(Session session) {
    if (session == null) {
      throw new NotFoundException();
    }
  }

  public static Registration createRegistration(NsUser nsUser, Session session) {
    Registration registration = new Registration(nsUser, session);
    session.register(nsUser, registration);
    return registration;
  }

  public void cancel() {
    canceled = true;
  }

  public boolean isCanceled() {
    return canceled;
  }

  public boolean hasNsUser(NsUser nsUser) {
    return this.nsUser.matchUser(nsUser);
  }
}
