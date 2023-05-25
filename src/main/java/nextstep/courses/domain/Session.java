package nextstep.courses.domain;


import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

/**
 * 모집중 일때만 수강 신청 가능
 */
public class Session extends BaseTimeEntity {

  private final Long id;

  private final SessionInfo sessionInfo;

  private final Image coverImage;

  private final SessionType sessionType;

  private final SessionStatus sessionStatus;

  private final Students students;

  private final SessionPeriod sessionPeriod;

  /**
   * 주 생성자
   */
  public Session(Long id, SessionInfo sessionInfo, Image coverImage, SessionType sessionType,
      SessionStatus sessionStatus, Students students, SessionPeriod sessionPeriod,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.sessionInfo = sessionInfo;
    this.coverImage = coverImage;
    this.sessionType = sessionType;
    this.sessionStatus = sessionStatus;
    this.students = students;
    this.sessionPeriod = sessionPeriod;
    hasNoUserForPreparingSession(sessionStatus);
  }


  /**
   * 부 생성자
   */
  Session(Long id, SessionInfo sessionInfo, Image image, SessionType sessionType,
      SessionStatus sessionStatus, Students students, SessionPeriod sessionPeriod) {
    this(id, sessionInfo, image, sessionType, sessionStatus, students, sessionPeriod,
        LocalDateTime.now(), LocalDateTime.now());
  }

  private void hasNoUserForPreparingSession(SessionStatus sessionStatus) {
    if (isPreparing(sessionStatus) && hasEnrolledUser()) {
      throw new IllegalArgumentException("준비중 상태일때는 수강생이 없어야 합니다.");
    }
  }

  private boolean hasEnrolledUser() {
    return !students.isEmpty();
  }

  private static boolean isPreparing(SessionStatus sessionStatus) {
    return sessionStatus.isPreparing();
  }

  public void enroll(NsUser user) {
    sessionStatus.validateEnrollAvailable();
    students.add(user);
  }

}
