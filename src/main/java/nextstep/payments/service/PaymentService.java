package nextstep.payments.service;

import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.qna.domain.CurrentDateTimeProvider;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final CurrentDateTimeProvider currentDateTimeProvider;

    public PaymentService(CurrentDateTimeProvider currentDateTimeProvider) {
        this.currentDateTimeProvider = currentDateTimeProvider;
    }

    public Payment pay(NsUser loginUser, Session session, Long amount) {
        return new Payment(
            session.getId(), loginUser.getId(),
            amount, currentDateTimeProvider.get()
        );
    }
}
