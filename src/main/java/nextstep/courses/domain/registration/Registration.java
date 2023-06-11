package nextstep.courses.domain.registration;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Registration {

  private RegistrationInfo registrationInfo;

  private NsUser nsUser;

  private Session session;

  private RegistrationStatus registrationStatus;

  public Registration(NsUser nsUser, Session session) {
    this(null, nsUser, session, LocalDateTime.now(), null, false);
  }

  public Registration(Long id, NsUser nsUser, Session session, LocalDateTime createdDate,
      LocalDateTime updatedDate, boolean canceled) {
    this(new RegistrationInfo(id, createdDate, updatedDate),
        nsUser,
        session,
        new RegistrationStatus(canceled));
  }

  public Registration(RegistrationInfo registrationInfo, NsUser nsUser,
      Session session, RegistrationStatus registrationStatus) {
    this.registrationInfo = registrationInfo;
    this.nsUser = nsUser;
    this.session = session;
    this.registrationStatus = registrationStatus;
  }

  public static Registration createRegistration(NsUser nsUser, Session session) {
    Registration registration = new Registration(nsUser, session);
    session.register(nsUser, registration);
    return registration;
  }

  public void cancel() {
    registrationStatus.cancel();
  }

  public boolean isCanceled() {
    return registrationStatus.isCanceled();
  }

  public boolean hasNsUser(NsUser nsUser) {
    return this.nsUser.matchUser(nsUser);
  }
}
