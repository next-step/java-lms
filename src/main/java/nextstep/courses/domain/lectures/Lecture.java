package nextstep.courses.domain.lectures;

import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUser;

public interface Lecture {

  boolean isFree();
  boolean isRecruiting();
  void canEnrollment(NsUser nsUser, Students selectedStudents);
  void enrollment(NsUser nsUser, Students selectedStudents);
  Lecture recruitingStart();
  Lecture start();
  Integer numberOfStudent();
}
