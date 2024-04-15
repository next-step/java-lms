package nextstep.payments.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;

public class PaymentService {

    private Payments payments;
    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }
}
