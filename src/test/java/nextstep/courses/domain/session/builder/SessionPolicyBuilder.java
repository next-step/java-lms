package nextstep.courses.domain.session.builder;

import nextstep.courses.domain.session.Capacity;
import nextstep.courses.domain.session.SessionPolicy;
import nextstep.courses.domain.session.Tuition;
import nextstep.courses.domain.session.constant.SessionType;

public class SessionPolicyBuilder {

    private Capacity maxCapacity = new Capacity(100);
    private Tuition tuition = new Tuition(300_000L);
    private SessionType sessionType = SessionType.PAID;

    public SessionPolicyBuilder withMaxCapacity(Capacity maxCapacity) {
        this.maxCapacity = maxCapacity;
        return this;
    }

    public SessionPolicyBuilder withMaxCapacity(int maxCapacity) {
        this.maxCapacity = new Capacity(maxCapacity);
        return this;
    }

    public SessionPolicyBuilder withTuition(Tuition tuition) {
        this.tuition = tuition;
        return this;
    }

    public SessionPolicyBuilder withSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
        return this;
    }

    public SessionPolicy build() {
        return new SessionPolicy(maxCapacity, tuition, sessionType);
    }

}
