package nextstep.courses.domain;


import java.time.LocalDateTime;

public class Session extends BaseTimeEntity {

  private final Long id;

  private final SessionInfo sessionInfo;

  private final Image coverImage;

  private final SessionType sessionType;

  private final SessionStatus sessionStatus;

  private final EnrolledUsers enrolledUsers;

  private final SessionPeriod sessionPeriod;

  public Session(Long id, SessionInfo sessionInfo, Image coverImage, SessionType sessionType,
      SessionStatus sessionStatus, EnrolledUsers enrolledUsers, SessionPeriod sessionPeriod,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.sessionInfo = sessionInfo;
    this.coverImage = coverImage;
    this.sessionType = sessionType;
    this.sessionStatus = sessionStatus;
    this.enrolledUsers = enrolledUsers;
    this.sessionPeriod = sessionPeriod;
  }


}
