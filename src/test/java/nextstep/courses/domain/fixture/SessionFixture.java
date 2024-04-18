package nextstep.courses.domain.fixture;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionFactory;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;

import static nextstep.courses.domain.fixture.IdFixture.COURSE_ID;
import static nextstep.courses.domain.fixture.SessionPeriodFixture.period;
import static nextstep.courses.domain.fixture.SessionStatusFixture.status;

public class SessionFixture {

    public static Session freeSession(SessionType type) {
        return SessionFactory.get(COURSE_ID, type, period(), status(), SessionCapacity.INFINITY, SessionFee.FREE);
    }

    public static Session paidSession(SessionType type, int capacity, long fee) {
        return SessionFactory.get(COURSE_ID, type, period(), status(), capacity, fee);
    }

}
