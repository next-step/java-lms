package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.student.Student;
import nextstep.courses.error.exception.SessionRegisterFailException;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class SessionEnrollment implements Enrollment {

    private final EnrollmentCount enrollmentCount;

    private final SessionState sessionState;

    private final Money tuitionFee;

    public SessionEnrollment(EnrollmentCount enrollmentCount, SessionState sessionState,
        Money tuitionFee) {
        this.enrollmentCount = enrollmentCount;
        this.sessionState = sessionState;
        this.tuitionFee = tuitionFee;
    }

    public Student enroll(NsUser nsUser, Payment payment) {
        if(checkRegistrationConditions(payment)){
            enrollmentCount.addRegistrationCount();
            return new Student(nsUser, payment);
        }

        throw new SessionRegisterFailException();
    }

    @Override
    public boolean checkRegistrationConditions(Payment payment) {
        if (!isPaymentAmountSameTuitionFee(payment)){
            return false;
        }

        if (!isRegistrationPossible()){
            return false;
        }

        return true;
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment) {
        return payment.isSamePaymentAmount(tuitionFee);
    }

    @Override
    public boolean isRegistrationPossible() {
        return sessionState.isRecruitmentOpen();
    }
}
