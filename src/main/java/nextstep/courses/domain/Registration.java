package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Registration {
  private final Long id;
  private final Session session;
  private final NsUser user;
  private RegisterStatus status;

  public Registration(final Session session, final NsUser user) {
    this(0L, session, user, RegisterStatus.PENDING);
  }

  public Registration(final Long id, final Session session, final NsUser user, final RegisterStatus status) {
    this.id = id;
    this.session = session;
    this.user = user;
    this.status = status;
  }

  public Session session() {
    return this.session;
  }

  public NsUser user() {
    return this.user;
  }

  public Long sessionId() {
    return this.session.getId();
  }

  public Long userId() {
    return this.user.getId();
  }

  public RegisterStatus getStatus() {
    return this.status;
  }

  public void approve() {
    this.status = RegisterStatus.APPROVED;
  }

  public void reject() {
    this.status = RegisterStatus.REJECTED;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Registration that = (Registration) o;
    return session.equals(that.session) && user.equals(that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(session, user);
  }

  @Override
  public String toString() {
    return "Registration{" +
            "id=" + id +
            ", session=" + session +
            ", user=" + user +
            '}';
  }
}
