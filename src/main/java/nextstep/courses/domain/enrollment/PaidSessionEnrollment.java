package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.error.exception.SessionRegisterFailException;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class PaidSessionEnrollment implements Enrollment {

    private final StudentName studentName;

    private final Long sessionId;

    private final Money tuitionFee;

    public PaidSessionEnrollment(StudentName studentName, Long sessionId, Money tuitionFee) {
        this.studentName = studentName;
        this.sessionId = sessionId;
        this.tuitionFee = tuitionFee;
    }

    public Session enroll(Session session, Payment payment) {
        if (session.isRegistrationAvailable() && isPaymentAmountSameTuitionFee(payment)) {
            session.addRegistrationCount();
            return session;
        }
        throw new SessionRegisterFailException();
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment) {
        return false;
    }
}
