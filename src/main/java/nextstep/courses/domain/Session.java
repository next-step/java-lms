package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;
import nextstep.users.domain.NextStepUsers;

import java.time.LocalDateTime;

public class Session {

  private final Long id;
  private final SessionPayment sessionPayment;
  private SessionStatus sessionStatus;
  private final MaxUserEnrollment maxUserEnrollment;
  private final NextStepUsers nextStepUsers = new NextStepUsers();
  private final SessionDate startDate;
  private final SessionDate closedDate;
  private final SessionCoverUrl sessionCoverUrl;

  public Session(Long id, SessionPayment sessionPayment, SessionStatus sessionStatus, int maxUserEnrollment, LocalDateTime startDate, LocalDateTime closedDate, String sessionCoverUrl) {
    this.startDate = new SessionDate(startDate);
    this.startDate.validateClosedDate(closedDate);

    this.id = id;
    this.sessionPayment = sessionPayment;
    this.sessionStatus = sessionStatus;
    this.maxUserEnrollment = new MaxUserEnrollment(maxUserEnrollment);
    this.closedDate = new SessionDate(closedDate);
    this.sessionCoverUrl = new SessionCoverUrl(sessionCoverUrl);
  }

  public int currentEnrollmentCount() {
    return nextStepUsers.currentEnrollment();
  }

  public void changeSessionStatus(SessionStatus sessionStatus) {
    this.sessionStatus = sessionStatus;
  }

  public void processEnrollment(NextStepUser nextStepUser) {
    sessionStatus.validateAcceptingStatus();
    maxUserEnrollment.validateUserEnrollment(nextStepUsers);

    nextStepUsers.enroll(nextStepUser);
  }

}
