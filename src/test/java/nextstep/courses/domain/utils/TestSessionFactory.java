package nextstep.courses.domain.utils;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDateTest;
import nextstep.courses.domain.SessionImageTest;
import nextstep.courses.domain.RecruitStatus;

public class TestSessionFactory {

    static public Session recruitStatusSession(Long id) {
        return new TestSession(id, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of());
    }

    static public Session makeSession(Long id, RecruitStatus recruitStatus) {
        return new TestSession(id, SessionImageTest.S1, recruitStatus, SessionDateTest.of());
    }

    static public Session recruitStatusSession2(Long id) {
        return new TestDuplicateSession(id, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of());
    }
}
