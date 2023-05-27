package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.DuplicatedException;
import nextstep.courses.RegistrationFulledException;
import nextstep.courses.RegistrationNotOpenedException;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;

public class Session {

  private Long id;

  private String title;

  private String img;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private SessionStatus sessionStatus = SessionStatus.PREPARATION;

  private SessionType sessionType;

  private int maxRecruitment;

  private Registrations registrations = new Registrations();

  public Session(String title, LocalDateTime startDate, LocalDateTime endDate, String img,
      SessionType sessionType, int maxRecruitment) {
    this(null, title, startDate, endDate, img, sessionType, maxRecruitment);
  }

  public Session(Long id, String title, LocalDateTime startDate, LocalDateTime endDate,
      String img, SessionType sessionType, int maxRecruitment) {
    this.id = id;
    this.title = title;
    validateDate(startDate, endDate);
    this.startDate = startDate;
    this.endDate = endDate;
    this.img = img;
    validateSessionType(sessionType);
    this.sessionType = sessionType;
    validateMaxRecruitment(maxRecruitment);
    this.maxRecruitment = maxRecruitment;
  }

  private void validateMaxRecruitment(int maxRecruitment) {
    if (maxRecruitment < 1) {
      throw new IllegalArgumentException();
    }
  }

  private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate == null || endDate == null) {
      throw new IllegalArgumentException();
    }

    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException();
    }
  }

  private void validateSessionType(SessionType sessionType) {
    if (sessionType == null) {
      throw new NotFoundException();
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
