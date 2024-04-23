package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.error.exception.SessionRegisterFailException;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class SessionEnrollment implements Enrollment {

    private final StudentName studentName;

    private final Long sessionId;

    private final Money tuitionFee;

    public SessionEnrollment(StudentName studentName, Long sessionId, Money tuitionFee) {
        this.studentName = studentName;
        this.sessionId = sessionId;
        this.tuitionFee = tuitionFee;
    }

    public void enroll(Session session, Payment payment) {
        if (session.isRegistrationAvailable() && isPaymentAmountSameTuitionFee(payment)) {
            session.addRegistrationCount();
            return;
        }

        throw new SessionRegisterFailException();
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment) {
        return payment.isSamePaymentAmount(tuitionFee);
    }

    @Override
    public StudentName getStudentName() {
        return studentName;
    }

    @Override
    public Long getSessionId() {
        return sessionId;
    }

    @Override
    public Money getTuitionFee() {
        return tuitionFee;
    }
}
