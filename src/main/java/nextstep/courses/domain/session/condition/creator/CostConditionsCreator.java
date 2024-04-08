package nextstep.courses.domain.session.condition.creator;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.domain.session.condition.SessionCapacityCondition;
import nextstep.courses.domain.session.condition.SessionEnrollmentCondition;
import nextstep.courses.domain.session.condition.SessionFeeCondition;

import java.util.List;

public class CostConditionsCreator implements ConditionsCreator {

    private final SessionFee sessionFee;
    private final Long paymentAmount;
    private final SessionCapacity capacity;

    public CostConditionsCreator(SessionFee sessionFee, Long paymentAmount, SessionCapacity capacity) {
        this.sessionFee = sessionFee;
        this.paymentAmount = paymentAmount;
        this.capacity = capacity;
    }

    @Override
    public List<SessionEnrollmentCondition> create() {
        return List.of(
                new SessionFeeCondition(sessionFee, paymentAmount),
                new SessionCapacityCondition(capacity)
        );
    }

}
