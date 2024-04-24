package nextstep.payments.service;

import java.util.concurrent.ThreadLocalRandom;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public Payment payment(Long sessionId, NsUser nsUser){
        return new Payment(String.valueOf(ThreadLocalRandom.current().nextLong()),
            sessionId,
            ThreadLocalRandom.current().nextLong(),
            nsUser.getMoney());
    }
}
