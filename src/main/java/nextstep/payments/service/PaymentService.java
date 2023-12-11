package nextstep.payments.service;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaymentService {
    public Payment payment(NsUser user, Session session, Long amount) {
        Registration registration = new Registration();
        registration.register(user, session, amount);
        return new Payment(session.id());
    }
}
