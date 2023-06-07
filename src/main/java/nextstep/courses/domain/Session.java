package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.DuplicatedException;
import nextstep.courses.RegistrationFulledException;
import nextstep.courses.RegistrationNotOpenedException;
import nextstep.users.domain.NsUser;

public class Session {

  private Long id;

  private String title;

  private String img;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Batch batch;

  private SessionStatus sessionStatus = SessionStatus.PREPARATION;

  private SessionType sessionType;

  private int maxRecruitment;

  private Registrations registrations = new Registrations();

  private Long creatorId;

  private LocalDateTime createdAt = LocalDateTime.now();

  private LocalDateTime updatedAt;

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
    this.id = id;
    this.title = title;
    this.img = img;
    validateDate(startDate, endDate);
    this.startDate = startDate;
    this.endDate = endDate;
    this.batch = batch;
    this.sessionStatus = sessionStatus;
    this.sessionType = sessionType;
    validateMaxRecruitment(maxRecruitment);
    this.maxRecruitment = maxRecruitment;
    this.registrations = registrations;
    this.creatorId = creatorId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate == null || endDate == null) {
      throw new IllegalArgumentException();
    }

    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException();
    }
  }

  private void validateMaxRecruitment(int maxRecruitment) {
    if (maxRecruitment < 1) {
      throw new IllegalArgumentException();
    }
  }

  public void register(NsUser nsUser, Registration registration) {
    validateRegister(nsUser);
    registrations.register(registration);
  }

  private void validateRegister(NsUser nsUser) {
    if (isRegistrationOpened()) {
      throw new RegistrationNotOpenedException("강의 상태가 모집중이 아닙니다.");
    }

    if (registrations.isRegistrationFulled(maxRecruitment)) {
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
    return !sessionStatus.equals(SessionStatus.RECRUITMENT);
  }

  public void registerOpen() {
    sessionStatus = SessionStatus.RECRUITMENT;
  }

  public void registerClose() {
    sessionStatus = SessionStatus.COMPLETION;
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
    return maxRecruitment == session.maxRecruitment && Objects.equals(id, session.id)
        && Objects.equals(title, session.title) && Objects
        .equals(img, session.img) && Objects.equals(startDate, session.startDate)
        && Objects.equals(endDate, session.endDate)
        && sessionStatus == session.sessionStatus && sessionType == session.sessionType;
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, title, img, startDate, endDate, sessionStatus, sessionType, maxRecruitment);
  }
}
