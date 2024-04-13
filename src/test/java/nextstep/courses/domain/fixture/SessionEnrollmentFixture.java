package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.PaidSessionEnrollment;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;

public class SessionEnrollmentFixture {

    public static FreeSessionEnrollment freeSessionEnrollment() {
        return new FreeSessionEnrollment(SESSION_ID, SessionStatus.RECRUITING);
    }

    public static SessionEnrollment freeSessionEnrollment(SessionStatus status) {
        return new FreeSessionEnrollment(SESSION_ID, status);
    }

    public static SessionEnrollment costSessionEnrollment(SessionStatus status, int capacity, long fee) {
        return new PaidSessionEnrollment(SESSION_ID, status, capacity, fee);
    }

}
