package nextstep.courses.domain;

import exception.LmsException;
import java.time.LocalDateTime;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;

public class Session {
  private Long id;
  private Course course;
  private SessionCoverImage coverImage;
  private SessionType sessionType;
  private SessionStatus sessionStatus;

  private SessionCapacity sessionCapacity;

  private LocalDateTime startAt;
  private LocalDateTime finishAt;

  public Session(Long id, Course course, SessionCoverImage coverImage, SessionType sessionType, SessionStatus sessionStatus, int maxPersonnelCount,  LocalDateTime startAt, LocalDateTime finishAt) {
    this.id = id;
    this.sessionType = sessionType;
    this.sessionStatus = sessionStatus;
    this.sessionCapacity = new SessionCapacity(maxPersonnelCount);
    this.course = course;
    this.coverImage = coverImage;
    this.startAt = startAt;
    this.finishAt = finishAt;
  }

  public void addPersonnel(NsUser nsUser) {
    if (this.sessionStatus != SessionStatus.RECRUITING) {
      throw new LmsException(SessionExceptionCode.ONLY_RECRUITING_STATUS_ALLOWED);
    }

    sessionCapacity.addPersonnel(nsUser);
  }

  public int getPersonnelSize() {
    return this.sessionCapacity.getCurrentSize();
  }
}
