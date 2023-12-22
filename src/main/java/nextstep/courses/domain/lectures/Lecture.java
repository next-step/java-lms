package nextstep.courses.domain.lectures;

import nextstep.users.domain.NsUser;

public interface Lecture {

  boolean isFree();
  boolean isRecruiting();
  void canEnrollment();
  void enrollment(NsUser nsUser);
  Lecture recruitingStart();
  Lecture start();
  Integer numberOfStudent();
}
