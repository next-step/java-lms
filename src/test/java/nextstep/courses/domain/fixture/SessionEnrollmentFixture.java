package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.PaidSessionEnrollment;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;

public class SessionEnrollmentFixture {

    public static SessionEnrollment freeSessionEnrollment() {
        return new FreeSessionEnrollment(SessionStatus.RECRUITING);
    }

    public static SessionEnrollment freeSessionEnrollment(SessionStatus status) {
        return new FreeSessionEnrollment(status);
    }

    public static SessionEnrollment paidSessionEnrollment(SessionStatus status, int capacity, long fee) {
        return new PaidSessionEnrollment(status, capacity, fee);
    }

}
