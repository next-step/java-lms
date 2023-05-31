package nextstep.courses.domain;

import nextstep.courses.domain.session.teacher.SessionTeacher;
import nextstep.users.domain.NsUserTest;

public class SessionTeacherTest {

  public static final SessionTeacher T4 = new SessionTeacher(
      1L,
      SessionTest.S4,
      NsUserTest.SANJIGI
  );

  public static final SessionTeacher T5 = new SessionTeacher(
      2L,
      SessionTest.S5,
      NsUserTest.SANJIGI
  );

  public static final SessionTeacher T6 = new SessionTeacher(
      3L,
      SessionTest.S6,
      NsUserTest.SANJIGI
  );

}
