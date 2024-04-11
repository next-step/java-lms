package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.engine.SessionEnrollment;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ENROLLMENT_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionFeeFixture.sessionFee;

public class SessionEnrollmentFixture {

    public static FreeSessionEnrollment freeSessionEnrollment() {
        return new FreeSessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, SessionStatus.RECRUITING, sessionCapacity(), sessionFee());
    }

    public static SessionEnrollment freeSessionEnrollment(SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        return new FreeSessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, status, capacity, fee);
    }

    public static SessionEnrollment costSessionEnrollment(SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        return new FreeSessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, status, capacity, fee);
    }

}
