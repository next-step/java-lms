package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

  private List<Registration> registrations = new ArrayList<>();

  public Session() {
  }

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
    isEnrollmentFull(maxRecruitment);
    this.maxRecruitment = maxRecruitment;
  }

  private void isEnrollmentFull(int maxRecruitment) {
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

  public Registration createRegistration(NsUser nsUser) {
    validateRegister();

    Registration registration = new Registration(this, nsUser);
    registrations.add(registration);
    return registration;
  }

  private void validateRegister() {
    if (isRegisterOpen()) {
      throw new RegistrationNotOpenedException("강의 상태가 모집중이 아닙니다.");
    }

    if (isRecruitmentFull()) {
      registerClose();
      throw new RegistrationFulledException("최대 수강 인원이 가득 찼습니다.");
    }
  }

  private boolean isRegisterOpen() {
    return !sessionStatus.equals(SessionStatus.RECRUITMENT);
  }

  private boolean isRecruitmentFull() {
    int registerCount = (int) registrations.stream()
        .filter(registration -> !registration.isCanceled())
        .count();

    return registerCount >= maxRecruitment;
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
