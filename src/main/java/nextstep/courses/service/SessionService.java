package nextstep.courses.service;

import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final PaymentService paymentService;

    public SessionService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void joinSession(NsUser loginUser, Session session) {
        session.join(loginUser);
    }

    public void joinPaidSession(NsUser loginUser, PaidSession paidSession, Payment payment) {
        paidSession.join(loginUser, payment);
    }
}
