package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class SessionUsers {

  private static final String MAX_ENROLLMENT_MESSAGE = "해당 세션의 수강 인원이 만석되었습니다.";
  private final int maxUserEnrollment;
  private final List<SessionUser> sessionUsers;

  public SessionUsers(int maxUserEnrollment) {
    this.maxUserEnrollment = maxUserEnrollment;
    this.sessionUsers = new ArrayList<>();
  }

  public SessionUsers(int maxUserEnrollment, List<SessionUser> sessionUsers) {
    this.maxUserEnrollment = maxUserEnrollment;
    this.sessionUsers = sessionUsers;
  }

  public void approveUserEnrollment(SessionUser sessionUser) {
    if (!canEnrollUser()) {
      throw new IllegalArgumentException(MAX_ENROLLMENT_MESSAGE);
    }

    sessionUsers.add(sessionUser);
  }

  public void enroll(SessionUser sessionUser) {
    sessionUsers.add(sessionUser);
  }

  public int currentEnrollment() {
    return sessionUsers.size();
  }

  private boolean canEnrollUser() {
    return sessionUsers.size() < maxUserEnrollment;
  }

  public int getMaxUserEnrollment() {
    return maxUserEnrollment;
  }

  public List<SessionUser> getSessionUsers() {
    return sessionUsers;
  }

  @Override
  public String toString() {
    return "SessionUsers{" +
            "maxUserEnrollment=" + maxUserEnrollment +
            ", sessionUsers=" + sessionUsers +
            '}';
  }
}
