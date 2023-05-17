package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;
import nextstep.users.domain.NextStepUsers;

public class Session {

  private final Long id;
  private final SessionPayment sessionPayment;
  private SessionStatus sessionStatus = SessionStatus.PREPARING;
  private final MaxUserEnrollment maxUserEnrollment;
  private final NextStepUsers nextStepUsers = new NextStepUsers();

  public Session(Long id, SessionPayment sessionPayment, int maxUserEnrollment) {
    this.id = id;
    this.sessionPayment = sessionPayment;
    this.maxUserEnrollment = new MaxUserEnrollment(maxUserEnrollment);
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
