package nextstep.courses.domain;

import nextstep.courses.domain.vo.Price;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidEnrollCommand implements EnrollCommand {

    private NsUser nsUser;
    private Payment payment;

    public PaidEnrollCommand(NsUser nsUser, Payment payment) {
        this.nsUser = nsUser;
        this.payment = payment;
    }

    @Override
    public NsUser userToEnroll(Price price) {
        if (price.paid(payment.amount())) {
            return nsUser;
        }
        throw new IllegalStateException("수강료와 유저 결제금액 불일치");
    }

}
