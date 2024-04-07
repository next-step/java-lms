package nextstep.payments.service;

import nextstep.courses.domain.session.PaidSession;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaymentService {
    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public Payment pay(Payment payment) {
        if (payment.failed()) {
            throw new IllegalArgumentException("결제를 실패했습니다.");
        }
        return payment;
    }
}
