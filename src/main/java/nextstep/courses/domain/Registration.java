package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;

public class Registration {

    private final PaymentService paymentService;

    public Registration(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Payment register(NsUser user, Session session, Long amount) {
        session.enroll(user, amount);
        return paymentService.payment(String.valueOf(session.id));
    }
}
