package nextstep.courses.domain.utils;

import nextstep.courses.domain.*;

import java.util.List;

public class TestSessionFactory {

    static public Session recruitStatusSession(Long id) {
        return new TestSession(id, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of());
    }

    static public Session makeSession(Long id, RecruitStatus recruitStatus) {
        return new TestSession(id, SessionImageTest.S1, recruitStatus, SessionDateTest.of());
    }

    static public Session recruitStatusOtherSession(Long id) {
        return new TestDuplicateSession(id, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of());
    }

    static public Session recruitSession(Long id, List<SessionImage> sessionImages) {
        return new TestSession(id, sessionImages, RecruitStatus.RECRUIT, SessionDateTest.of());
    }

    static public Session recruitStatusSession2(Long id) {
        return new TestDuplicateSession(id, SessionImageTest.S1, SessionStatus.RECRUIT, SessionDateTest.of());
    }
}
