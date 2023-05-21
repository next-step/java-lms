package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class EnrolledUsers {

  private final List<NsUser> users = new ArrayList<>();
  private final MaxEnrollment maxEnrollment;

  public EnrolledUsers(MaxEnrollment maxEnrollment) {
    this.maxEnrollment = maxEnrollment;
  }

}
