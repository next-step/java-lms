package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.PaidSessionEnrollment;
import nextstep.courses.domain.status.SessionStatus;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;

import static nextstep.courses.domain.fixture.SessionStatusFixture.status;

public class SessionEnrollmentFixture {

    public static SessionEnrollment freeSessionEnrollment() {
        return new FreeSessionEnrollment(status());
    }

    public static SessionEnrollment freeSessionEnrollment(SessionStatus status) {
        return new FreeSessionEnrollment(status);
    }

    public static SessionEnrollment paidSessionEnrollment(SessionStatus status, int capacity, long fee) {
        return new PaidSessionEnrollment(status, capacity, fee);
    }

}
