package nextstep.payments.service;

import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public Payment payment(Long sessionId, Long nsUserId, Long amount) {
        String transactionKey = approve(amount);
        return new Payment(transactionKey, sessionId, nsUserId, amount);
    }

    private String approve(Long amount) {
        // 강의 수강료 금액에 대해 PG사 결제 요청
        return "1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed";
    }
}
