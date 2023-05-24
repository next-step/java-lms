package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;

public class SessionUser {

  private final Long id;
  private final Session session;
  private final NextStepUser nextStepUser;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public SessionUser(Session session, NextStepUser nextStepUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(null, session, nextStepUser, createdAt, updatedAt);
  }

  public SessionUser(Long id, Session session, NextStepUser nextStepUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.session = session;
    this.nextStepUser = nextStepUser;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
