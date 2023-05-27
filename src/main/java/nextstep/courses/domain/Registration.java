package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

public class Registration {

  private Long id;

  private Session session;

  private NsUser nsUser;

  private LocalDateTime registeredAt;

  private boolean canceled = false;

  public Registration() {
  }

  public Registration(Session session, NsUser nsUser) {
    this(null, session, nsUser, LocalDateTime.now());
  }

  public Registration(Long id, Session session, NsUser nsUser, LocalDateTime registeredAt) {
    this.id = id;
    this.session = session;
    this.nsUser = nsUser;
    this.registeredAt = registeredAt;
  }

  public boolean isCanceled(){
    return canceled;
  }
}
