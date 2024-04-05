package nextstep.payments.service;

import nextstep.courses.domain.PaidSession;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class PaymentService {
    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public Payment pay(NsUser nsUser, PaidSession paidSession) {
        //TODO 돈이 안맞으면 예외 처리
        return new Payment();
    }
}
