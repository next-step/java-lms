package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidJoinStrategy implements JoinStrategy {
    @Override
    public boolean joinable(Session session, Payment pay) {
        return session.recruiting() &&
                session.underCapacity() &&
                session.tuitionMatched(pay.getAmount());
    }
}
