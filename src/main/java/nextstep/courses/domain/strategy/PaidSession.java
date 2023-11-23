package nextstep.courses.domain.strategy;

import nextstep.courses.domain.*;
import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
import nextstep.courses.exception.IncorrectAmountException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession implements Session {
    private final Period period;
    private final Thumbnail thumbnail;
    private final PaidStudents paidStudents;
    private final Amount amount;
    private final SessionStatus status;

    public PaidSession(Period period,
                       Thumbnail thumbnail,
                       PaidStudents paidStudents,
                       Amount amount,
                       SessionStatus status) {
        this.paidStudents = paidStudents;
        this.period = period;
        this.thumbnail = thumbnail;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public boolean isSupport(SessionType sessionType) {
        return SessionType.PAID == sessionType;
    }

    @Override
    public void apply(Payment payment,
                      NsUser student) {
        validateAmount(payment);
        validateStatus();

        paidStudents.add(student);
    }

    private void validateAmount(Payment payment) {
        if (!amount.isSameAmount(payment)) {
            throw new IncorrectAmountException("결제 금액과 강의 금액이 다릅니다.");
        }
    }

    private void validateStatus() {
        status.validateApply();
    }

}
