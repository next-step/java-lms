package nextstep.courses.domain.session.type;

import nextstep.courses.domain.session.EnrollmentCondition;

public class Paid implements SessionType {
    private final long sessionId;
    private final long price;

    public Paid(long sessionId, long price) {
        this.sessionId = sessionId;
        this.price = price;
    }

    @Override
    public boolean canEnroll(EnrollmentCondition condition) {
        return condition.hasPaid(this.sessionId, price);
    }

    @Override
    public String toHumanReadableTypeName() {
        return "유료";
    }
}
