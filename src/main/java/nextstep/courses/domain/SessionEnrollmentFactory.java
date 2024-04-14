package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.PaidSessionEnrollment;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.exception.SessionEnrollmentNotMatchException;

public class SessionEnrollmentFactory {

    public static SessionEnrollment get(SessionType type, SessionStatus status) {
        if (SessionType.FREE.equals(type)) {
            return new FreeSessionEnrollment(status);
        }
        throw new SessionEnrollmentNotMatchException(type);
    }

    public static SessionEnrollment get(SessionType type, SessionStatus status, int capacity, long fee) {
        if (SessionType.PAID.equals(type)) {
            return new PaidSessionEnrollment(status, capacity, fee);
        }

        throw new SessionEnrollmentNotMatchException(type);
    }


}
