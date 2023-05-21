package nextstep.courses.domain;


import java.time.LocalDateTime;

/**
 * 모집중 일때만 수강 신청 가능
 */
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


  Session(Long id, SessionInfo sessionInfo, Image image, SessionType sessionType,
      SessionStatus sessionStatus, EnrolledUsers enrolledUsers, SessionPeriod sessionPeriod) {
    super();
    this.id = id;
    this.sessionInfo = sessionInfo;
    this.coverImage = image;
    this.sessionType = sessionType;
    this.sessionStatus = sessionStatus;
    this.enrolledUsers = enrolledUsers;
    this.sessionPeriod = sessionPeriod;
    validateSessionStatus(sessionStatus);
  }

  private void validateSessionStatus(SessionStatus sessionStatus) {
    if (isPreparing(sessionStatus) && hasEnrolledUser()) {
      throw new IllegalArgumentException("준비중 상태일때는 수강생이 없어야 합니다.");
    }
  }

  private boolean hasEnrolledUser() {
    return !enrolledUsers.isEmpty();
  }

  private static boolean isPreparing(SessionStatus sessionStatus) {
    return sessionStatus == SessionStatus.PREPARING;
  }
}
