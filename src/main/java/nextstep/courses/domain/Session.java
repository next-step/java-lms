package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;
import nextstep.users.domain.NextStepUsers;

import java.time.LocalDateTime;

public class Session {

  private static final String NOT_ACCEPTING_MESSAGE = "수강신청은 강의 상태가 모집중일 때만 가능합니다. 현재 수강 상태 : ";
  private final Long id;
  private final SessionPayment sessionPayment;
  private SessionStatus sessionStatus;
  private final NextStepUsers nextStepUsers;
  private final SessionPeriod sessionPeriod;
  private final SessionCoverUrl sessionCoverUrl;

  public Session(Long id, SessionPayment sessionPayment, SessionStatus sessionStatus, int maxUserEnrollment, LocalDateTime startDate, LocalDateTime endDate, String sessionCoverUrl) {
    this.id = id;
    this.sessionPayment = sessionPayment;
    this.sessionStatus = sessionStatus;
    this.nextStepUsers = new NextStepUsers(maxUserEnrollment);
    this.sessionPeriod = new SessionPeriod(startDate, endDate);
    this.sessionCoverUrl = new SessionCoverUrl(sessionCoverUrl);
  }

  public int currentEnrollmentCount() {
    return nextStepUsers.currentEnrollment();
  }

  public void changeSessionStatus(SessionStatus sessionStatus) {
    this.sessionStatus = sessionStatus;
  }

  public void processEnrollment(NextStepUser nextStepUser) {
    if (!sessionStatus.canEnroll()) {
      throw new IllegalArgumentException(NOT_ACCEPTING_MESSAGE + sessionStatus.status());
    }

    nextStepUsers.enroll(nextStepUser);
  }

}
