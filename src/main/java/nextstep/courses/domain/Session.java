package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;
import nextstep.users.domain.NextStepUsers;

public class Session {

  private final Long id;
  private final SessionPayment sessionPayment;
  private SessionStatus sessionStatus;
  private final MaxUserEnrollment maxUserEnrollment;
  private final NextStepUsers nextStepUsers = new NextStepUsers();
  private final SessionDate startDate;
  private final SessionDate closedDate;

  public Session(Long id, SessionPayment sessionPayment, SessionStatus sessionStatus, int maxUserEnrollment, SessionDate startDate, SessionDate closedDate) {
    startDate.validateClosedDate(closedDate);

    this.id = id;
    this.sessionPayment = sessionPayment;
    this.sessionStatus = sessionStatus;
    this.maxUserEnrollment = new MaxUserEnrollment(maxUserEnrollment);
    this.startDate = startDate;
    this.closedDate = closedDate;
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
