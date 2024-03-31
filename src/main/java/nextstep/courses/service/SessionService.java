package nextstep.courses.service;

import javax.annotation.Resource;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Resource(name = "paymentService")
    private PaymentService paymentService;

    public void joinSession(NsUser loginUser, Session session, Payment payment) {
        session.join(loginUser, payment);
    }
}
