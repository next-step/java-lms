package nextstep.courses.domain;


public class Session extends BaseTimeEntity{

  private Long id;

  private SessionInfo sessionInfo;

  private Image coverImage;

  private SessionType sessionType;

  private SessionStatus sessionStatus;

  private EnrolledUsers enrolledUsers;

  private SessionPeriod sessionPeriod;

  public Session(Long id, SessionInfo sessionInfo, Image coverImage, SessionType sessionType,
      SessionStatus sessionStatus, EnrolledUsers enrolledUsers, SessionPeriod sessionPeriod) {
    this.id = id;
    this.sessionInfo = sessionInfo;
    this.coverImage = coverImage;
    this.sessionType = sessionType;
    this.sessionStatus = sessionStatus;
    this.enrolledUsers = enrolledUsers;
    this.sessionPeriod = sessionPeriod;
  }
}
