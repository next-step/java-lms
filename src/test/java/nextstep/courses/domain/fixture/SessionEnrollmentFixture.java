package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.domain.session.SessionEnrollment;
import nextstep.courses.domain.session.SessionEnrollmentConditions;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ENROLLMENT_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionFeeFixture.sessionFee;
import static nextstep.courses.domain.session.SessionStatus.RECRUITING;

public class SessionEnrollmentFixture {


    public static SessionEnrollment sessionEnrollment(SessionCapacity capacity) {
        return new SessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, capacity, RECRUITING, new SessionEnrollmentConditions(), sessionFee());
    }

    public static SessionEnrollment sessionEnrollment(SessionEnrollmentConditions enrollmentConditions) {
        return new SessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, sessionCapacity(),RECRUITING, enrollmentConditions, sessionFee());
    }

    public static SessionEnrollment sessionEnrollment(Long fee, SessionEnrollmentConditions enrollmentConditions) {
        return new SessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, sessionCapacity(), RECRUITING, enrollmentConditions, sessionFee(fee));
    }

    public static SessionEnrollment sessionEnrollment(SessionCapacity capacity, Long fee, SessionEnrollmentConditions enrollmentConditions) {
        return new SessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, capacity, RECRUITING, enrollmentConditions, sessionFee(fee));
    }

}
