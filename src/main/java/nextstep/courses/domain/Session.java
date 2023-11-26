package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {
    private final Period period;
    private final Thumbnail thumbnail;
    private final Enrollment enrollment;
    private final Amount amount;
    private final SessionStatus status;

    public Session(Period period,
                   Thumbnail thumbnail,
                   Enrollment enrollment,
                   Amount amount,
                   SessionStatus status) {
        this.enrollment = enrollment;
        this.period = period;
        this.thumbnail = thumbnail;
        this.amount = amount;
        this.status = status;
    }

    public void enrol(Payment payment,
                      NsUser student) {
        amount.validateAmount(payment);
        status.validateApply();

        enrollment.enrol(student);
    }

}
