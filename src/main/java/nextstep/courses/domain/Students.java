package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

/**
 * 최대 인원수를 넘어서 수강 신청할 수 없다.
 */
public class Students {

  private final List<NsUser> users;
  private final MaxEnrollment maxEnrollment;

  public Students(List<NsUser> users, MaxEnrollment maxEnrollment) {
    this.users = users;
    this.maxEnrollment = maxEnrollment;
  }

  public Students(MaxEnrollment maxEnrollment) {
    this(new ArrayList<>(), maxEnrollment);
  }

  public boolean isEmpty() {
    return users.isEmpty();
  }


  public void add(NsUser user) {
    if (isFull()) {
      throw new IllegalArgumentException("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
    }
    users.add(user);
  }

  private boolean isFull() {
    return maxEnrollment.isFull(users.size());
  }

  public int size() {
    return users.size();
  }
}
