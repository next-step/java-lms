package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;

public class SessionUser {

  private Long id;
  private final Session session;
  private final NextStepUser nextStepUser;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
  private SessionUserStatus sessionUserStatus;

  public SessionUser(Session session, NextStepUser nextStepUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(null, session, nextStepUser, createdAt, updatedAt, SessionUserStatus.REQUEST);
  }

  public SessionUser(Session session, NextStepUser nextStepUser, LocalDateTime createdAt, LocalDateTime updatedAt, SessionUserStatus sessionUserStatus) {
    this(null, session, nextStepUser, createdAt, updatedAt, sessionUserStatus);
  }

  public SessionUser(Long id, Session session, NextStepUser nextStepUser, LocalDateTime createdAt, LocalDateTime updatedAt, SessionUserStatus sessionUserStatus) {
    this.id = id;
    this.session = session;
    this.nextStepUser = nextStepUser;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.sessionUserStatus = sessionUserStatus;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Session getSession() {
    return session;
  }

  public NextStepUser getNextStepUser() {
    return nextStepUser;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public String getSessionUserStatus() {
    return sessionUserStatus.status();
  }

  public void approve() {
    this.sessionUserStatus = SessionUserStatus.APPROVAL;
  }

  public void reject() {
    this.sessionUserStatus = SessionUserStatus.REJECTION;
  }
}
