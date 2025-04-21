package nextstep.courses.domain.session.policy;

import lombok.Getter;
import nextstep.courses.domain.session.constraint.SessionConstraint;

import java.util.Objects;

@Getter
public class SessionEnrollPolicy {

    private final EnrollmentStatus enrollmentStatus;

    private final SessionStatus status;

    private final SessionType type;

    public SessionEnrollPolicy() {
        this(EnrollmentStatus.NOT_ENROLLING, SessionStatus.PREPARING, SessionType.FREE);
    }

    public SessionEnrollPolicy(EnrollmentStatus enrollmentStatus, SessionStatus status, SessionType type) {
        this.enrollmentStatus = enrollmentStatus;
        this.status = status;
        this.type = type;
    }

    public boolean canEnroll(SessionConstraint sessionConstraint, int enrollCount, long amount) {
        return type.canEnroll(sessionConstraint, enrollCount, amount) && status.canEnroll() && enrollmentStatus.canEnroll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionEnrollPolicy that = (SessionEnrollPolicy) o;
        return status == that.status && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, type);
    }
}
