package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.PaidSessionEnrollment;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.SessionStudent;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.exception.SessionEnrollmentNotMatchException;

import java.util.List;

public class SessionEnrollmentFactory {

    public static SessionEnrollment get(SessionType type, SessionStatus status, int capacity, long fee) {
        if (SessionType.FREE.equals(type)) {
            return new FreeSessionEnrollment(status);
        }

        if (SessionType.PAID.equals(type)) {
            return new PaidSessionEnrollment(status, capacity, fee);
        }

        throw new SessionEnrollmentNotMatchException(type);
    }

    public static SessionEnrollment get(Long sessionId, SessionType type, SessionStatus status, int capacity, long fee) {
        if (SessionType.FREE.equals(type)) {
            return new FreeSessionEnrollment(sessionId, status);
        }

        if (SessionType.PAID.equals(type)) {
            return new PaidSessionEnrollment(sessionId, status, capacity, fee);
        }

        throw new SessionEnrollmentNotMatchException(type);
    }

    public static SessionEnrollment get(Session session, List<SessionStudent> students) {
        if (SessionType.FREE.equals(session.getType())) {
            return new FreeSessionEnrollment(session.getEnrollment(), students);
        }

        if (SessionType.PAID.equals(session.getType())) {
            return new PaidSessionEnrollment(session.getEnrollment(), students);
        }

        throw new SessionEnrollmentNotMatchException(session.getType());
    }

}
