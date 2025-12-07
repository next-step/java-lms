package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Session {
    private final Long id;
    private final int cohort;
    private final SessionPeriod period;
    private final SessionImage coverImage;
    private final Enrollment enrollment;

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status) {
        this(new SessionPeriod(startDate, endDate), coverImage, SessionStatus.from(status), new FreeSessionType());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage image, String status, int maximumCapacity, long fee) {
        this(new SessionPeriod(startDate, endDate), image, SessionStatus.from(status), new PaidSessionType(maximumCapacity, fee));
    }

    public Session(SessionPeriod period, SessionImage coverImage, SessionStatus status, SessionType sessionType) {
        this(1, period, coverImage, new Enrollment(status, sessionType));
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image) {
        this(cohort, new SessionPeriod(startDate, endDate), image, new Enrollment(SessionStatus.PREPARING, new FreeSessionType()));
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, Enrollment enrollment) {
        this(cohort, new SessionPeriod(startDate, endDate), image, enrollment);
    }

    public Session(int cohort, SessionPeriod period, SessionImage coverImage, Enrollment enrollment) {
        this(null, cohort, period, coverImage, enrollment);
    }

    public Session(long id, int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, Enrollment enrollment) {
        this(id, cohort, new SessionPeriod(startDate, endDate), image, enrollment);
    }

    public Session(Long id, int cohort, SessionPeriod period, SessionImage coverImage, Enrollment enrollment) {
        this.id = id;
        this.cohort = cohort;
        this.period = period;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    public Enrollment createEnrollment(List<EnrolledStudent> currentStudents) {
        return new Enrollment(id, enrollment.getStatus(), enrollment.getSessionType(), currentStudents);
    }

    public Long getId() {
        return id;
    }

    public int getCohort() {
        return cohort;
    }

    public SessionImage getImage() {
        return coverImage;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public LocalDate getStartDate() {
        return period.getStartDate();
    }

    public LocalDate getEndDate() {
        return period.getEndDate();
    }

}
