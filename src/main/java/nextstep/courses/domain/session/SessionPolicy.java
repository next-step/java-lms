package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.SessionType;
import nextstep.payments.domain.Payment;

import java.util.Objects;

public class SessionPolicy {

    private final Capacity maxCapacity;
    private final Tuition tuition;
    private final SessionType sessionType;

    public SessionPolicy(int maxCapacity, Long tuition, String sessionType) {
        this(new Capacity(maxCapacity), new Tuition(tuition), SessionType.from(sessionType.toUpperCase()));
    }

    public SessionPolicy(Capacity maxCapacity, Tuition tuition, SessionType sessionType) {
        this.maxCapacity = maxCapacity;
        this.tuition = tuition;
        this.sessionType = sessionType;
    }

    public boolean matchAmount(Payment payment) {
        return this.tuition.matchAmount(payment);
    }

    public boolean matchSize(int enrollmentsSize) {
        return this.maxCapacity.matchSize(enrollmentsSize);
    }

    public Capacity getMaxCapacity() {
        return maxCapacity;
    }

    public Tuition getTuition() {
        return tuition;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public boolean isSessionType() {
        return this.sessionType.equals(SessionType.PAID);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessionPolicy that = (SessionPolicy) o;
        return Objects.equals(getMaxCapacity(), that.getMaxCapacity()) && Objects.equals(getTuition(), that.getTuition()) && isSessionType() == that.isSessionType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaxCapacity(), getTuition(), isSessionType());
    }
}
