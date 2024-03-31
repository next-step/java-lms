package nextstep.payments.service;

import javax.annotation.Resource;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.qna.domain.CurrentDateTimeProvider;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentService {

    @Resource(name = "localDateTimeProvider")
    private CurrentDateTimeProvider currentDateTimeProvider;

    public Payment pay(NsUser loginUser, Session session, Long amount) {
        return new Payment(
            session.getId(), loginUser.getId(),
            amount, currentDateTimeProvider.get()
        );
    }
}
