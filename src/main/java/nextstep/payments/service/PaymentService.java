package nextstep.payments.service;

import nextstep.payments.domain.Payment;

import java.util.List;

public class PaymentService {
    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    //sessionId 에 맞는 결제 정보를 가져왔다고 가정
    public List<Payment> findBySessionId(Long sessionId) {
        return List.of(new Payment(), new Payment());
    }

    public Payment findBySessionIdAndNsUserId(Long sessionId, Long nsUserId) {
        //sessionId 와 nsUserId 에 맞는 결제 정보를 가져왔다고 가정
        return new Payment();
    }
}
