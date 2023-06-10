package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.DuplicatedException;
import nextstep.courses.RegistrationFulledException;
import nextstep.courses.RegistrationNotOpenedException;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.batch.Batch;
import nextstep.users.domain.NsUser;

public class Session {

  private SessionInfo sessionInfo;

  private SessionPeriod sessionPeriod;

  private Batch batch;

  private Enrollment enrollment;

  private Registrations registrations = new Registrations();

  private BaseInfo baseInfo;

  public Session(String title, String img, LocalDateTime startDate, LocalDateTime endDate,
      Batch batch, SessionType sessionType, int maxRecruitment, Long creatorId) {
    this(null, title, img, startDate, endDate, batch, SessionStatus.PREPARATION, sessionType,
        maxRecruitment, new Registrations(), creatorId, LocalDateTime.now(), null);
  }

  public Session(Long id, String title, String img, LocalDateTime startDate,
      LocalDateTime endDate, Batch batch, SessionStatus sessionStatus,
      SessionType sessionType, int maxRecruitment,
      Registrations registrations, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this(new SessionInfo(id, title, img),
        new SessionPeriod(startDate, endDate),
        batch,
        new Enrollment(sessionStatus, sessionType, maxRecruitment),
        registrations,
        new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Session(SessionInfo sessionInfo, SessionPeriod sessionPeriod,
      Batch batch, Enrollment enrollment, Registrations registrations,
      BaseInfo baseInfo) {
    this.sessionInfo = sessionInfo;
    this.sessionPeriod = sessionPeriod;
    this.batch = batch;
    this.enrollment = enrollment;
    this.registrations = registrations;
    this.baseInfo = baseInfo;
  }

  public void register(NsUser nsUser, Registration registration) {
    validateRegister(nsUser);
    registrations.register(registration);
  }

  private void validateRegister(NsUser nsUser) {
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

  public boolean hasNsUser(NsUser nsUser) {
    return registrations.hasNsUser(nsUser);
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
    return Objects.equals(sessionInfo, session.sessionInfo) && Objects
        .equals(sessionPeriod, session.sessionPeriod) && Objects
        .equals(batch, session.batch) && Objects.equals(enrollment, session.enrollment)
        && Objects.equals(registrations, session.registrations) && Objects
        .equals(baseInfo, session.baseInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionInfo, sessionPeriod, batch, enrollment, registrations, baseInfo);
  }
}
