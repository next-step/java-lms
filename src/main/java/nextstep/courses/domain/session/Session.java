package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private final int cohort;
    private final SessionPeriod period;
    private final SessionImage coverImage;
    private final Enrollment enrollment;

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status) {
        this(new SessionPeriod(startDate, endDate), coverImage, SessionStatus.from(status), new HashSet<>(), new FreeSessionType());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage image, String status, int maximumCapacity, long fee) {
        this(new SessionPeriod(startDate, endDate), image, SessionStatus.from(status), new HashSet<>(), new PaidSessionType(maximumCapacity, fee));
    }

    public Session(SessionPeriod period, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds, SessionType sessionType) {
        this(1, period, coverImage, new Enrollment(status, sessionType, enrolledStudentIds));
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image) {
        this(cohort, new SessionPeriod(startDate, endDate), image, new Enrollment(SessionStatus.PREPARING, new FreeSessionType()));
    }

    public Session(int cohort, SessionPeriod period, SessionImage coverImage, Enrollment enrollment) {
        this.cohort = cohort;
        this.period = period;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    public void enroll(Long studentId) {
        enrollment.enroll(studentId);
    }

    public void enroll(Long studentId, Payment pay) {
        enrollment.enroll(studentId, pay);
    }

    public boolean isEnrolled(Long studentId) {
        return enrollment.isEnrolled(studentId);
    }

    public int getCohort() {
        return cohort;
    }
}
