package nextstep.payments.service;

import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment payment(NsUser user, Session session, Long amount) {
        return new Payment(session.id());
    }
}
