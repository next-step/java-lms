package nextstep.session.domain.teacher;

import nextstep.session.domain.SessionTest;
import nextstep.users.domain.NsUserTest;

public class SessionTeacherTest {
    public static final SessionTeacher T1 = new SessionTeacher(
            1L, SessionTest.s1, NsUserTest.JAVAJIGI
    );
    public static final SessionTeacher T2 = new SessionTeacher(
            2L, SessionTest.s2, NsUserTest.SANJIGI
    );
}
