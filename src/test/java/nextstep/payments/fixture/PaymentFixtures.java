package nextstep.payments.fixture;

import nextstep.payments.domain.Payment;

public class PaymentFixtures {
    public static Payment payment() {
        return payment(1L, 1L);
    }

    public static Payment differentPayment() {
        return payment(999L, 999L);
    }

    public static Payment payment(Long sessionId, Long nsUserId) {
        return new Payment("1", sessionId, nsUserId, 1000L);
    }
}
