package nextstep.courses.domain.session;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.PaidSessionEnrollment;
import nextstep.courses.domain.enrollment.RecruitmentStatus;
import nextstep.courses.domain.enrollment.SessionPeriod;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.student.SessionStudents;
import nextstep.courses.exception.SessionEnrollmentNotMatchException;

import java.time.LocalDateTime;
import java.util.List;

public class SessionFactory {

    public static Session get(Long courseId, SessionType type, SessionPeriod period, ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, int capacity, long fee) {
        if (SessionType.FREE == type) {
            return new Session(courseId, type, period, progressStatus, new FreeSessionEnrollment(recruitmentStatus));
        }

        if (SessionType.PAID == type) {
            return new Session(courseId, type, period, progressStatus, new PaidSessionEnrollment(recruitmentStatus, capacity, fee));
        }

        throw new SessionEnrollmentNotMatchException(type);
    }

    public static Session get(Long sessionId, Long courseId, String typeString, LocalDateTime startAt, LocalDateTime endAt, String progressString, String recruitmentString, int capacity, long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        SessionType type = SessionType.convert(typeString);
        ProgressStatus progressStatus = ProgressStatus.convert(progressString);
        RecruitmentStatus recruitmentStatus = RecruitmentStatus.convert(recruitmentString);

        if (SessionType.FREE == type) {
            return new Session(sessionId, courseId, type, new SessionPeriod(startAt, endAt), progressStatus, new FreeSessionEnrollment(recruitmentStatus), createdAt, updatedAt);
        }

        if (SessionType.PAID == type) {
            return new Session(sessionId, courseId, type, new SessionPeriod(startAt, endAt), progressStatus, new PaidSessionEnrollment(recruitmentStatus, capacity, fee), createdAt, updatedAt);
        }

        throw new SessionEnrollmentNotMatchException(type);
    }

    public static Session get(Session session, List<SessionCoverImage> coverImages, SessionStudents students) {
        SessionEnrollment enrollment = assembleEnrollmentStudents(session, students);
        return new Session(session.getId(), session.getCourseId(), session.getType(), session.getPeriod(), coverImages, enrollment, session.getCreatedAt(), session.getUpdatedAt());
    }

    private static SessionEnrollment assembleEnrollmentStudents(Session session, SessionStudents students) {
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
