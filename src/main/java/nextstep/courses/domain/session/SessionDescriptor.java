package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionDescriptor {

    private final SessionPeriod period;

    private final SessionEnrollPolicy policy;

    public SessionDescriptor(SessionPeriod period, SessionEnrollPolicy policy) {
        this.period = period;
        this.policy = policy;
    }

    public LocalDateTime startDate() {
        return period.startDate();
    }

    public LocalDateTime endDate() {
        return period.endDate();
    }

    public String status() {
        return policy.status();
    }

    public String type() {
        return policy.type();
    }

    public boolean canEnroll(SessionConstraint sessionConstraint, int enrollCount, long amount) {
        return policy.canEnroll(sessionConstraint, enrollCount, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDescriptor that = (SessionDescriptor) o;
        return Objects.equals(period, that.period) && Objects.equals(policy, that.policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, policy);
    }
}
