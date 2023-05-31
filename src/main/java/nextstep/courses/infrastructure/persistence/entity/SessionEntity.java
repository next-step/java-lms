package nextstep.courses.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.BaseTimeEntity;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.MaxEnrollment;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionInfo;
import nextstep.courses.domain.SessionPeriod;
import nextstep.courses.domain.SessionRecruitStatus;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;


public class SessionEntity extends BaseTimeEntity {

  private Long id;

  private Long courseId;

  private SessionInfo sessionInfo;

  private Long coverImageId;

  private SessionType sessionType;

  // TODO : 변경 사항 발생
  private SessionStatus sessionStatus;

  // TODO : 변경 사항 발생
  private SessionRecruitStatus sessionRecruitStatus;

  private int maxEnrollmentSize;

  private SessionPeriod sessionPeriod;

  public SessionEntity() {
  }

  /**
   * 주 생성자
   */
  public SessionEntity(Long id, Long courseId, SessionInfo sessionInfo, Long coverImageId,
      SessionType sessionType, SessionStatus sessionStatus, SessionRecruitStatus sessionRecruitStatus, int maxEnrollmentSize,
      SessionPeriod sessionPeriod, LocalDateTime createAd, LocalDateTime updatedAt) {
    super(createAd, updatedAt);
    this.id = id;
    this.courseId = courseId;
    this.sessionInfo = sessionInfo;
    this.coverImageId = coverImageId;
    this.sessionType = sessionType;
    this.sessionStatus = sessionStatus;
    this.sessionRecruitStatus = sessionRecruitStatus;
    this.maxEnrollmentSize = maxEnrollmentSize;
    this.sessionPeriod = sessionPeriod;
  }

  /**
   * 부 생성자.
   * sessionStatusRef 가 준비중, 끝남은 => 비모집중으로 entity 조회
   * sessionStatusRef 가 모집중 (앞전 데이터)라면 => 진행중, 모집중으로 변경 필요
   *
   */
  public SessionEntity(Long id, Long courseId, String title, String description, Long coverImageId,
      String sessionType, String sessionStatus, String sessionRecruitStatus,int maxEnrollmentSize, LocalDateTime startDateTime,
      LocalDateTime endDateTime, LocalDateTime createAt, LocalDateTime updatedAt) {
    this(id, courseId, new SessionInfo(title, description), coverImageId, SessionType.valueOf(sessionType),
        convertSessionStatus(sessionStatus), convertSessionRecruitStatus(sessionStatus, sessionRecruitStatus), maxEnrollmentSize, new SessionPeriod(startDateTime, endDateTime),
        createAt, updatedAt);
  }

  /**
   * 기존 DataBase에 (old_모집중)으로 저장된 경우, (new_진행중)으로 변경해서 객체 상태를 판단할 수 있도록 한다.
   */
  private static SessionStatus convertSessionStatus(String sessionStatus) {
    if (SessionStatus.valueOf(sessionStatus).isRecruiting()) {
      return SessionStatus.IN_PROGRESS;
    }

    return SessionStatus.valueOf(sessionStatus);
  }

  /**
   * [리뷰 전]
   * 데이터 마이그레이션 전에는, SessionRecruitStatus 상태가 없는 경우는 SessionStatus 상태를 보고 판단
   * SessionRecruitStatus == null && SessionStatus 가 모집중이면 => SessionRecruitStatus == 모집중
   * SessionRecruitStatus == null && SessionStatus 가 준비중, 끝남이면 => SessionRecruitStatus == 비모집중
   *
   * [리뷰 후]
   * 데이터 마이그레이션 전에는, SessionRecruitStatus 상태가 없는 경우 Table Default 에 의해 NOT_RECRUITING 상태로 저장
   * 따라서, 기존 SessionStatus에 (준비중,종료)를 갖는 데이터는 SessionRecruitStatus에 NOT_RECRUITING 상태로 관리하고자 함
   * 만약, 기존 SessionStatus가 (old_모집중)이라면 SessionRecruitStatus에는 RECRUITING 상태로 관리하고자 함
   * 새로운 SessionRecruitStatus column 추가 후로 새롭게 저장된 데이터는 그에 맞는 SessionRecruitStatus 상태로 관리하고자 함
   */
  private static SessionRecruitStatus convertSessionRecruitStatus(String sessionStatus, String sessionRecruitStatus) {
    if (SessionStatus.valueOf(sessionStatus).isRecruiting()) {
      return SessionRecruitStatus.RECRUITING;
    }

    return SessionRecruitStatus.of(sessionRecruitStatus);
  }

  public Long getCoverImageId() {
    return coverImageId;
  }

  public Session toDomain(List<Student> students, Image image) {
    return new Session(id, sessionInfo, image, sessionType, sessionStatus,
        sessionRecruitStatus, new Students(students, new MaxEnrollment(maxEnrollmentSize)), sessionPeriod);
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
