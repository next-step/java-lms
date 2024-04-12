package nextstep.payments.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public Payment pay(Session session, NsUser requestUser) {
        return new Payment(null, session.id(), requestUser.getId(), session.price());
    }
}
