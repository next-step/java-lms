package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.qna.NotFoundException;

public class Session {

  private Long id;

  private String title;

  private String img;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private SessionStatus sessionStatus = SessionStatus.PREPARATION;

  private SessionType sessionType;

  private int maxRecruitment;

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
