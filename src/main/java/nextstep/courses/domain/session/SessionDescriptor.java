package nextstep.courses.domain.session;

import lombok.Getter;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;

import java.util.Objects;

@Getter
public class SessionDescriptor {

    private final SessionPeriod period;

    private final SessionEnrollPolicy policy;

    private final SessionImages images;

    public SessionDescriptor(SessionPeriod period, SessionEnrollPolicy policy, SessionImages images) {
        this.period = period;
        this.policy = policy;
        this.images = images;
    }

    public boolean canEnroll(SessionConstraint sessionConstraint, int enrollCount, long amount) {
        return policy.canEnroll(sessionConstraint, enrollCount, amount);
    }

    public void deleteSessionImage() {
        images.delete();
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
