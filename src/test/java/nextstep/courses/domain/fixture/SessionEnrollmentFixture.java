package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.domain.session.SessionEnrollment;
import nextstep.courses.domain.session.condition.SessionConditions;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.exception.ExceedSessionCapacityException;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ENROLLMENT_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionFeeFixture.sessionFee;
import static nextstep.courses.domain.session.SessionStatus.RECRUITING;

public class SessionEnrollmentFixture {

    public static SessionEnrollment sessionEnrollment(SessionConditions conditions) throws ExceedSessionCapacityException {
        return new SessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, sessionCapacity(),RECRUITING, conditions, sessionFee());
    }

    public static SessionEnrollment sessionEnrollment(SessionCapacity capacity, SessionFee fee, SessionConditions conditions) {
        return new SessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, capacity, RECRUITING, conditions, fee);
    }

    public static SessionEnrollment sessionEnrollment(Long fee, SessionConditions conditions) throws ExceedSessionCapacityException {
        return new SessionEnrollment(SESSION_ENROLLMENT_ID, SESSION_ID, sessionCapacity(), RECRUITING, conditions, sessionFee(fee));
    }

}
