package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session {

  private static final String NOT_ACCEPTING_MESSAGE = "수강신청은 강의 상태가 모집중일 때만 가능합니다. 현재 수강 상태 : ";
  private Long id;
  private final SessionPayment sessionPayment;
  private final SessionUsers sessionUsers;
  private final SessionPeriod sessionPeriod;
  private final SessionCoverUrl sessionCoverUrl;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
  private final SessionStatus sessionStatus;

  public Session(SessionPayment sessionPayment, SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus, int maxUserEnrollment, LocalDateTime startDate, LocalDateTime endDate, String sessionCoverUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(null, sessionPayment, sessionProgressStatus, sessionRecruitmentStatus, maxUserEnrollment, startDate, endDate, sessionCoverUrl, createdAt, updatedAt);
  }

  public Session(Long id, SessionPayment sessionPayment, SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus, int maxUserEnrollment, List<SessionUser> sessionUsers, LocalDateTime startDate, LocalDateTime endDate, String sessionCoverUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.sessionPayment = sessionPayment;
    this.sessionUsers = new SessionUsers(maxUserEnrollment, sessionUsers);
    this.sessionPeriod = new SessionPeriod(startDate, endDate);
    this.sessionCoverUrl = new SessionCoverUrl(sessionCoverUrl);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.sessionStatus = new SessionStatus(sessionProgressStatus, sessionRecruitmentStatus);
  }

  public Session(Long id, SessionPayment sessionPayment, SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus, int maxUserEnrollment, LocalDateTime startDate, LocalDateTime endDate, String sessionCoverUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.sessionPayment = sessionPayment;
    this.sessionUsers = new SessionUsers(maxUserEnrollment);
    this.sessionPeriod = new SessionPeriod(startDate, endDate);
    this.sessionCoverUrl = new SessionCoverUrl(sessionCoverUrl);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.sessionStatus = new SessionStatus(sessionProgressStatus, sessionRecruitmentStatus);
  }

  public int currentEnrollmentCount() {
    return sessionUsers.currentEnrollment();
  }

  public void ending() {
    sessionStatus.ending();
  }

  public void processEnrollment(NextStepUser nextStepUser) {
    if (!sessionStatus.canEnroll()) {
      throw new IllegalArgumentException(NOT_ACCEPTING_MESSAGE + sessionStatus.getProgressStatus());
    }

    LocalDateTime now = LocalDateTime.now();
    sessionUsers.enroll(new SessionUser(this, nextStepUser, now, now, SessionUserStatus.REQUEST));
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SessionPayment getSessionPayment() {
    return sessionPayment;
  }

  public SessionUsers getSessionUsers() {
    return sessionUsers;
  }

  public SessionPeriod getSessionPeriod() {
    return sessionPeriod;
  }

  public SessionCoverUrl getSessionCoverUrl() {
    return sessionCoverUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public SessionStatus getSessionStatus() {
    return sessionStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Session session = (Session) o;

    return Objects.equals(id, session.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
