package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;

public class Registration {

  private Long id;

  private Session session;

  private NsUser nsUser;

  private LocalDateTime registeredAt;

  private boolean canceled = false;

  private Registration() {
  }

  public Registration(Session session, NsUser nsUser) {
    this(null, session, nsUser, LocalDateTime.now());
  }

  public Registration(Long id, Session session, NsUser nsUser, LocalDateTime registeredAt) {
    this.id = id;
    validateSession(session);
    this.session = session;
    validateNsUser(nsUser);
    this.nsUser = nsUser;
    this.registeredAt = registeredAt;
  }

  private void validateSession(Session session) {
    if (session == null) {
      throw new NotFoundException();
    }
  }

  private void validateNsUser(NsUser nsUser) {
    if (nsUser == null) {
      throw new NotFoundException();
    }
  }

  public static Registration createRegistration(Session session, NsUser nsUser) {
    Registration registration = new Registration(session, nsUser);
    session.register(registration);
    nsUser.register(session, registration);
    return registration;
  }

  public void cancel(){
    canceled = true;
  }

  public boolean isCanceled() {
    return canceled;
  }

  public boolean hasSession(Session session) {
    return this.session.equals(session);
  }
}
