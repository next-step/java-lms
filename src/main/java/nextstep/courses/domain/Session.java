package nextstep.courses.domain;

import java.util.Objects;

public class Session {

    private final Long id;
    private final SessionPeriod sessionPeriod;
    private final CoverImage coverImage;
    private final SessionPolicy sessionPolicy;
    private final SessionStatus sessionStatus;
    private final Enrollments enrollments;

    public Session(SessionPeriod sessionPeriod, CoverImage coverImage, SessionPolicy sessionPolicy) {
        this(0L, sessionPeriod, coverImage, sessionPolicy, SessionStatus.PREPARING, new Enrollments());
    }

    public Session(
            SessionPeriod sessionPeriod, CoverImage coverImage,
            SessionPolicy sessionPolicy, SessionStatus sessionStatus
    ) {
        this(0L, sessionPeriod, coverImage, sessionPolicy, sessionStatus, new Enrollments());
    }

    public Session(
            Long id, SessionPeriod sessionPeriod, CoverImage coverImage,
            SessionPolicy sessionPolicy, SessionStatus sessionStatus, Enrollments enrollments
    ) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionPolicy = sessionPolicy;
        this.sessionStatus = sessionStatus;
        this.enrollments = enrollments;
    }

    public void enroll(Long studentId, Money payment) {
        validateCanEnroll();
        sessionPolicy.validate(payment, this.currentEnrollmentCount());
        enrollments.add(new Enrollment(studentId, this.id));
    }

    private void validateCanEnroll() {
        if (!sessionStatus.canEnroll()) {
            throw new RuntimeException("현재 모집중인 강의가 아닙니다.");
        }
    }

    public int currentEnrollmentCount() {
        return enrollments.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id)
                && Objects.equals(sessionPeriod, session.sessionPeriod)
                && Objects.equals(coverImage, session.coverImage)
                && Objects.equals(sessionPolicy, session.sessionPolicy)
                && sessionStatus == session.sessionStatus
                && Objects.equals(enrollments, session.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionPeriod, coverImage, sessionPolicy, sessionStatus, enrollments);
    }
}
