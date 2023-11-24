package nextstep.courses.domain.strategy;

import nextstep.courses.domain.*;
import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
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
        amount.validateAmount(payment);
        status.validateApply();

        paidStudents.add(student);
    }

}
