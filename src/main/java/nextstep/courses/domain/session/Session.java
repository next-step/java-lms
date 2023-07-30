package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.DuplicatedException;
import nextstep.courses.RegistrationFulledException;
import nextstep.courses.RegistrationNotOpenedException;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;
import nextstep.users.domain.NsUser;

public class Session {

  private SessionInfo sessionInfo;

  private SessionPeriod sessionPeriod;

  private Enrollment enrollment;

  private BaseInfo baseInfo;

  public Session(String title, String img, LocalDateTime startDate, LocalDateTime endDate,
      SessionType sessionType, int maxRecruitment, Long creatorId) {
    this(null, title, img, startDate, endDate, sessionType, maxRecruitment, creatorId);
  }

  public Session(Long id, String title, String img, LocalDateTime startDate, LocalDateTime endDate,
      SessionType sessionType, int maxRecruitment, Long creatorId) {
    this(id, title, img
        , startDate, endDate
        , SessionStatus.PREPARATION, sessionType, maxRecruitment
        , creatorId, LocalDateTime.now(), LocalDateTime.now());
  }

  public Session(Long id, String title, String img, LocalDateTime startDate,
      LocalDateTime endDate, SessionStatus sessionStatus, SessionType sessionType,
      int maxRecruitment, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this(new SessionInfo(id, title, img),
        new SessionPeriod(startDate, endDate),
        new Enrollment(sessionStatus, sessionType, maxRecruitment),
        new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Session(SessionInfo sessionInfo, SessionPeriod sessionPeriod, Enrollment enrollment,
      BaseInfo baseInfo) {
    this.sessionInfo = sessionInfo;
    this.sessionPeriod = sessionPeriod;
    this.enrollment = enrollment;
    this.baseInfo = baseInfo;
  }

  public void register(NsUser nsUser, Registration registration, Registrations registrations) {
    validateRegister(nsUser, registrations);
    registrations.register(registration);
  }

  private void validateRegister(NsUser nsUser, Registrations registrations) {
    if (isRegistrationOpened()) {
      throw new RegistrationNotOpenedException("강의 상태가 모집중이 아닙니다.");
    }

    if (enrollment.isRegistrationFulled(registrations)) {
      throw new RegistrationFulledException("최대 수강 인원이 가득 찼습니다.");
    }

    if (registrations.hasNsUser(nsUser)) {
      throw new DuplicatedException("강의는 중복으로 신청할 수 없습니다.");
    }
  }

  private boolean isRegistrationOpened() {
    return enrollment.isRegistrationOpened();
  }

  public void registerOpen() {
    enrollment.registerOpen();
  }

  public void registerClose() {
    enrollment.registerClose();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Session session = (Session) o;
    return Objects.equals(sessionInfo, session.sessionInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionInfo);
  }

  public Long getId() {
    return sessionInfo.getId();
  }

  public String getTitle() {
    return sessionInfo.getTitle();
  }

  public String getImg() {
    return sessionInfo.getImg();
  }

  public LocalDateTime getStartDate() {
    return sessionPeriod.getStartDate();
  }

  public LocalDateTime getEndDate() {
    return sessionPeriod.getEndDate();
  }

  public SessionStatus getSessionStatus() {
    return enrollment.getSessionStatus();
  }

  public SessionType getSessionType() {
    return enrollment.getSessionType();
  }

  public int getMaxRecruitment() {
    return enrollment.getMaxRecruitment();
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }
}
