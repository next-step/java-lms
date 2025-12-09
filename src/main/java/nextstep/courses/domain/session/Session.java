package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private final Long id;
    private final int cohort;
    private final SessionPeriod period;
    private final SessionImage coverImage;
    private final SessionStatus status;
    private final SessionType sessionType;

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status) {
        this(new SessionPeriod(startDate, endDate), coverImage, SessionStatus.from(status), new FreeSessionType());
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus sessionStatus) {
        this(cohort, startDate, endDate, image, sessionStatus, new FreeSessionType());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage image, String status, int maximumCapacity, long fee) {
        this(new SessionPeriod(startDate, endDate), image, SessionStatus.from(status), new PaidSessionType(maximumCapacity, fee));
    }

    public Session(SessionPeriod sessionPeriod, SessionImage image, SessionStatus from, SessionType sessionType) {
        this(null, 1, sessionPeriod, image, from, sessionType);
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus status, SessionType type) {
        this(null, cohort, new SessionPeriod(startDate, endDate), image, status, type);
    }

    public Session(long id, int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus status, SessionType type) {
        this(id, cohort, new SessionPeriod(startDate, endDate), image, status, type);
    }

    public Session(Long id, int cohort, SessionPeriod period, SessionImage coverImage, SessionStatus status, SessionType sessionType) {
        this.id = id;
        this.cohort = cohort;
        this.period = period;
        this.coverImage = coverImage;
        this.status = status;
        this.sessionType = sessionType;
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image) {
        this(cohort, startDate, endDate, image, SessionStatus.PREPARING);
    }

    public Enrollment createEnrollment(List<EnrolledStudent> currentStudents) {
        return new Enrollment(id, status, sessionType, currentStudents);
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

    public SessionStatus getStatus() {
        return status;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public LocalDate getStartDate() {
        return period.getStartDate();
    }

    public LocalDate getEndDate() {
        return period.getEndDate();
    }

}
