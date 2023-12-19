package nextstep.courses.domain.lectures;

import nextstep.users.domain.NsUser;

public interface Lecture {

  boolean isFree();
  boolean recruiting();
  void canEnrollment();
  void enrollment(NsUser nsUser);
  Lecture recruitingStart();
  Integer numberOfStudent();
}
