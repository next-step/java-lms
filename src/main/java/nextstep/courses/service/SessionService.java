package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;

public class SessionService {

    public void register(Payment payment, Session session) {
        session.register(payment);
    }
}
