package nextstep.courses.domain.registration;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Registration {

  private final RegistrationInfo registrationInfo;

  private final Long nsUserId;

  private final Long sessionId;

  private final BaseInfo baseInfo;

  public Registration(Long nsUserId, Long sessionId, Long creatorId) {
    this(null, nsUserId, sessionId, creatorId);
  }

  public Registration(Long id, Long nsUserId, Long sessionId, Long creatorId) {
    this(id, RegistrationStatus.WAITING, nsUserId, sessionId
        , creatorId, LocalDateTime.now(), LocalDateTime.now());
  }

  public Registration(Long id, RegistrationStatus registrationStatus, Long nsUserId, Long sessionId
      , Long creatorId, LocalDateTime createdDate, LocalDateTime updatedDate) {
    this(new RegistrationInfo(id, registrationStatus),
        nsUserId,
        sessionId,
        new BaseInfo(creatorId, createdDate, updatedDate));
  }

  public Registration(RegistrationInfo registrationInfo, Long nsUserId, Long sessionId,
      BaseInfo baseInfo) {
    this.registrationInfo = registrationInfo;
    this.nsUserId = nsUserId;
    this.sessionId = sessionId;
    this.baseInfo = baseInfo;
  }

  public static Registration createRegistration(NsUser nsUser, Session session,
      Registrations registrations) {
    return createRegistration(nsUser, session, registrations, nsUser.getId());
  }

  public static Registration createRegistration(NsUser nsUser, Session session,
      Registrations registrations, Long creatorId) {
    Registration registration = new Registration(nsUser.getId(), session.getId(), creatorId);
    session.register(registration, registrations);
    return registration;
  }

  public void approval() {
    registrationInfo.approval();
  }

  public boolean isApproval() {
    return registrationInfo.isApproval();
  }

  public void cancel() {
    registrationInfo.cancel();
  }

  public boolean isCancel(){
    return registrationInfo.isCancel();
  }

  public boolean hasNsUser(Long nsUserId) {
    return this.nsUserId.equals(nsUserId);
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
    return Objects.equals(registrationInfo, that.registrationInfo) && Objects
        .equals(nsUserId, that.nsUserId) && Objects.equals(sessionId, that.sessionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registrationInfo, nsUserId, sessionId);
  }

  public Long getId() {
    return registrationInfo.getId();
  }

  public RegistrationStatus getRegistrationStatus() {
    return registrationInfo.getRegistrationStatus();
  }

  public Long getNsUserId() {
    return nsUserId;
  }

  public Long getSessionId() {
    return sessionId;
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }
}
