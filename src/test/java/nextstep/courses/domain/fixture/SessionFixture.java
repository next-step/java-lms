package nextstep.courses.domain.fixture;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionFactory;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.enrollment.SessionStatus;

import static nextstep.courses.domain.fixture.IdFixture.COURSE_ID;

public class SessionFixture {

    public static Session session(SessionType type) {
        return SessionFactory.get(COURSE_ID, type, SessionStatus.RECRUITING, SessionCapacity.INFINITY, SessionFee.FREE);
    }

}
