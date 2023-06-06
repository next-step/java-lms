package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Set;
import nextstep.users.domain.NsUser;

/**
 * 객체를 생성한 후에 validate 메서드를 통해 명시적으로 검증해야 한다
 *
 * @date 2023-06-03
 * @author jerryk026
 */
public class Session {

  private Long id;

  private SessionDate sessionDate;

  private SessionBody sessionBody;

  private SessionRegistration sessionRegistration;

  public Session(String title, String contents, LocalDateTime startDateTime, LocalDateTime endDateTime, byte[] coverImage, int capacity) {
    this(new SessionDate(startDateTime, endDateTime), new SessionBody(title, contents, coverImage), new SessionRegistration(capacity));
  }

  public Session(SessionDate sessionDate, SessionBody sessionBody, SessionRegistration sessionRegistration) {
    this.sessionDate = sessionDate;
    this.sessionBody = sessionBody;
    this.sessionRegistration = sessionRegistration;
  }

  public void recruitStart() {
    this.sessionRegistration.recruitStart();
  }

  public void recruitEnd() {
    this.sessionRegistration.recruitEnd();
  }

  public void enrollment(NsUser user, LocalDateTime enrollmentDateTime) {
    sessionDate.validateEnrolment(enrollmentDateTime);
    sessionRegistration.enrolment(user);
  }

  public void validateInit() {
    sessionDate.validateInit();
    sessionRegistration.validateInit();
  }

  public Set<NsUser> getUsers() {
    return this.sessionRegistration.getUsers();
  }
}
