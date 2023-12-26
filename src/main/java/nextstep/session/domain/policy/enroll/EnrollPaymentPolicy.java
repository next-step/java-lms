package nextstep.session.domain.policy.enroll;

import nextstep.payments.domain.Payment;
import nextstep.session.domain.PaidSession;
import nextstep.users.domain.NsUser;

public class EnrollPaymentPolicy implements EnrollPolicy<PaidSession> {
    @Override
    public void validate(PaidSession session, NsUser nsUser) {
        Payment payment = nsUser.getSessionPayment(session);
        if (!payment.getAmount().equals(session.getPrice())) {
            throw new IllegalArgumentException("강의의 가격과 결제한 가격이 다릅니다.");
        }
    }
}
