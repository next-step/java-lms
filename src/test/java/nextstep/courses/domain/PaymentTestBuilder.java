package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaymentTestBuilder {
    private String paymentId = "p1";
    private Long sessionId;
    private Long userId;
    private Money amount = new Money(5000);

    public static Payment validPaymentFor(Session session, Long userId) {
        return new Payment("p1", session.getId(), userId, 5000L);
    }

    public static Payment inValidPaymentFor(Session session, Long userId) {
        return new Payment("p1", session.getId(), userId, 3000L);
    }
}
