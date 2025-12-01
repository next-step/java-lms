package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private final SessionPeriod period;
    private final SessionImage coverImage;
    private final Enrollment enrollment;

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status) {
        this(startDate, endDate, coverImage, status, new HashSet<>());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status, Set<Long> enrolledStudentIds) {
        this(startDate, endDate, coverImage, SessionStatus.from(status), enrolledStudentIds);
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds) {
        this(new SessionPeriod(startDate, endDate), coverImage, status, enrolledStudentIds);
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage image, String status, int maximumCapacity, long fee) {
        this(new SessionPeriod(startDate, endDate), image, SessionStatus.from(status), new HashSet<>(), new PaidSessionType(maximumCapacity, fee));
    }

    public Session(SessionPeriod period, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds) {
        this(period, coverImage, new Enrollment(status, new FreeSessionType(), enrolledStudentIds));
    }

    public Session(SessionPeriod period, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds, SessionType sessionType) {
        this(period, coverImage, new Enrollment(status, sessionType, enrolledStudentIds));
    }

    public Session(SessionPeriod period, SessionImage coverImage, Enrollment enrollment) {
        this.period = period;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    public void enroll(Long studentId) {
        enrollment.enroll(studentId);
    }

    public void enroll(Long studentId, Long paymentAmount) {
        enrollment.enroll(studentId, paymentAmount);
    }

    public boolean isEnrolled(Long studentId) {
        return enrollment.isEnrolled(studentId);
    }
}
