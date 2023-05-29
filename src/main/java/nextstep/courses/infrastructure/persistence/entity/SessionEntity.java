package nextstep.courses.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.BaseTimeEntity;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.MaxEnrollment;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionInfo;
import nextstep.courses.domain.SessionPeriod;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUser;


public class SessionEntity extends BaseTimeEntity {

  private Long id;

  private Long courseId;

  private SessionInfo sessionInfo;

  private Long coverImageId;

  private SessionType sessionType;

  private SessionStatus sessionStatus;

  private int maxEnrollmentSize;

  private SessionPeriod sessionPeriod;

  public SessionEntity() {
  }

  /**
   * 주 생성자
   */
  public SessionEntity(Long id, Long courseId, SessionInfo sessionInfo, Long coverImageId,
      SessionType sessionType, SessionStatus sessionStatus, int maxEnrollmentSize,
      SessionPeriod sessionPeriod, LocalDateTime createAd, LocalDateTime updatedAt) {
    super(createAd, updatedAt);
    this.id = id;
    this.courseId = courseId;
    this.sessionInfo = sessionInfo;
    this.coverImageId = coverImageId;
    this.sessionType = sessionType;
    this.sessionStatus = sessionStatus;
    this.maxEnrollmentSize = maxEnrollmentSize;
    this.sessionPeriod = sessionPeriod;
  }

  /**
   * 부 생성자.
   */
  public SessionEntity(Long id, Long courseId, String title, String description, Long coverImageId,
      String sessionType, String sessionStatus, int maxEnrollmentSize, LocalDateTime startDateTime,
      LocalDateTime endDateTime, LocalDateTime createAt, LocalDateTime updatedAt) {
    this(id, courseId, new SessionInfo(title, description), coverImageId, SessionType.valueOf(sessionType),
        SessionStatus.valueOf(sessionStatus), maxEnrollmentSize, new SessionPeriod(startDateTime, endDateTime),
        createAt, updatedAt);
  }

  public Long getCoverImageId() {
    return coverImageId;
  }

  public Session toDomain(List<NsUser> students, Image image) {
    return new Session(id, sessionInfo, image, sessionType, sessionStatus,
        new Students(students, new MaxEnrollment(maxEnrollmentSize)), sessionPeriod, createdAt,
        updatedAt);
  }

  public Long getId() {
    return id;
  }

  public Long getCourseId() {
    return courseId;
  }

  public SessionInfo getSessionInfo() {
    return sessionInfo;
  }

  public SessionType getSessionType() {
    return sessionType;
  }

  public SessionStatus getSessionStatus() {
    return sessionStatus;
  }

  public int getMaxEnrollmentSize() {
    return maxEnrollmentSize;
  }

  public SessionPeriod getSessionPeriod() {
    return sessionPeriod;
  }

}
