package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public interface Lecture {

  boolean isFree();
  boolean recruiting();
  void enrollment(NsUser nsUser);
  Lecture start();
  Integer numberOfStudent();
}
