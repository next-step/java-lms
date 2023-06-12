package nextstep.sessions.domain;

import java.util.HashSet;
import java.util.Set;
import nextstep.users.domain.NsUser;

public class SessionRegistration {
  private int capacity;

  private SessionStatus status;

  private Set<NsUser> users;

  public SessionRegistration(int capacity) {
    this(capacity, SessionStatus.READY, new HashSet<>());
  }

  public SessionRegistration(int capacity, SessionStatus status, Set users) {
    this.capacity = capacity;
    this.status = status;
    this.users = users;
  }

  public void recruitStart() {
    this.status = SessionStatus.RECRUITING;
  }

  public void recruitEnd() {
    this.status = SessionStatus.END;
  }

  public void enrolment(NsUser user) {
    if (users.size() > capacity) {
      throw new IllegalStateException("수강인원이 초과되었습니다");
    }

    if (users.contains(user)) {
      throw new IllegalStateException("이미 수강신청한 사용자입니다");
    }

    SessionStatus.isRecruitingOrThrow(status);

    users.add(user);
  }

  public void validateInit() {
    if (capacity <= 0) {
      throw new IllegalArgumentException("수강인원 수는 1명 이상이어야 합니다");
    }
  }

  public int getCapacity() {
    return this.capacity;
  }

  public SessionStatus getStatus() {
    return this.status;
  }

  public Set<NsUser> getUsers() {
    return this.users;
  }

  @Override
  public String toString() {
    return "SessionRegistration{" +
        "capacity=" + capacity +
        ", status=" + status +
        ", users=" + users +
        '}';
  }
}
