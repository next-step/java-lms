package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

/**
 * 최대 인원수를 넘어서 수강 신청할 수 없다.
 */
public class EnrolledUsers {

  private final List<NsUser> users = new ArrayList<>();
  private final MaxEnrollment maxEnrollment;

  public EnrolledUsers(MaxEnrollment maxEnrollment) {
    this.maxEnrollment = maxEnrollment;
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
}
