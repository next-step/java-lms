package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.*;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.status.SessionStatus;
import nextstep.courses.exception.SessionEnrollmentNotMatchException;

import java.time.LocalDateTime;
import java.util.List;

public class SessionFactory {

    public static Session get(Long courseId, SessionType type, SessionPeriod period, SessionStatus status, int capacity, long fee) {
        if (SessionType.FREE == type) {
            return new Session(courseId, type, period, new FreeSessionEnrollment(status));
        }

        if (SessionType.PAID == type) {
            return new Session(courseId, type, period, new PaidSessionEnrollment(status, capacity, fee));
        }

        throw new SessionEnrollmentNotMatchException(type);
    }

    public static Session get(Long sessionId, Long courseId, String typeString, LocalDateTime startAt, LocalDateTime endAt, String progressString, String recruitmentString, int capacity, long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        SessionType type = SessionType.convert(typeString);
        SessionStatus status = SessionStatus.of(progressString, recruitmentString);

        if (SessionType.FREE == type) {
            return new Session(sessionId, courseId, type, new SessionPeriod(startAt, endAt), new FreeSessionEnrollment(status), createdAt, updatedAt);
        }

        if (SessionType.PAID == type) {
            return new Session(sessionId, courseId, type, new SessionPeriod(startAt, endAt), new PaidSessionEnrollment(status, capacity, fee), createdAt, updatedAt);
        }

        throw new SessionEnrollmentNotMatchException(type);
    }

    public static Session get(Session session, List<SessionStudent> students) {
        SessionEnrollment enrollment = assembleEnrollmentStudents(session, students);
        return new Session(session.getId(), session.getCourseId(), session.getType(), session.getPeriod(), enrollment, session.getCreatedAt(), session.getUpdatedAt());
    }

    public static Session get(Session session, SessionCoverImage coverImage, List<SessionStudent> students) {
        SessionEnrollment enrollment = assembleEnrollmentStudents(session, students);
        return new Session(session.getId(), session.getCourseId(), session.getType(), session.getPeriod(), coverImage, enrollment, session.getCreatedAt(), session.getUpdatedAt());
    }

    private static SessionEnrollment assembleEnrollmentStudents(Session session, List<SessionStudent> students) {
        SessionType type = session.getType();
        SessionEnrollment enrollment = session.getEnrollment();

        if (SessionType.FREE == type) {
            return new FreeSessionEnrollment(enrollment, students);
        }

        if (SessionType.PAID == type) {
            return new PaidSessionEnrollment(enrollment, students);
        }

        throw new SessionEnrollmentNotMatchException(type);
    }
}
