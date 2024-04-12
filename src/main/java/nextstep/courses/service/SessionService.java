package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    public void enroll(Session session, NsUser loginUser, Payment payment) {
        session.enroll(loginUser, payment);
    }
}
