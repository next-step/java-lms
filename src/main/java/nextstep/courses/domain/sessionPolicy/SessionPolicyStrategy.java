package nextstep.courses.domain.sessionPolicy;

import nextstep.payments.domain.Payment;

public interface SessionPolicyStrategy {

    int getCapacity();

    boolean isCompletePay(Payment payment);
}
