package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.domain.student.Student;
import nextstep.courses.error.exception.MaxRegistrationExceededException;
import nextstep.courses.error.exception.PaymentConditionNotMetException;
import nextstep.courses.error.exception.SessionNotOpenForEnrollmentException;
import nextstep.courses.error.exception.SessionRegisterFailException;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private final Long id;

    private final EnrollmentCount enrollmentCount;

    private final SessionState sessionState;

    private final Money tuitionFee;

    private final FeeType feeType;

    public Enrollment(Long id, EnrollmentCount enrollmentCount, SessionState sessionState,
        Money tuitionFee, FeeType feeType) {
        this.id = id;
        this.enrollmentCount = enrollmentCount;
        this.sessionState = sessionState;
        this.tuitionFee = tuitionFee;
        this.feeType = feeType;
    }

    public Student enroll(NsUser nsUser, Payment payment) {
        if (checkRegistrationConditions(payment)) {
            enrollmentCount.addRegistrationCount();
            return new Student(nsUser, payment);
        }

        throw new SessionRegisterFailException();
    }

    public boolean checkRegistrationConditions(Payment payment) {
        if (!isSatisfyPaymentCondition(payment)) {
            throw new PaymentConditionNotMetException(tuitionFee, payment);
        }

        if (!isSatisfySessionState()) {
            throw new SessionNotOpenForEnrollmentException(sessionState);
        }

        if (!isSatisfyCapacityCondition()) {
            throw new MaxRegistrationExceededException(enrollmentCount);
        }

        return true;
    }

    public boolean isSatisfyPaymentCondition(Payment payment) {
        return payment.isSamePaymentAmount(tuitionFee);
    }

    public boolean isSatisfySessionState() {
        return sessionState.isOnGoing() || sessionState.isRecruiting();
    }

    public boolean isSatisfyCapacityCondition() {
        return enrollmentCount.isRegistrationWithinCapacity();
    }

    public Long getId() {
        return id;
    }

    public EnrollmentCount getEnrollmentCount() {
        return enrollmentCount;
    }

    public SessionState getSessionState() {
        return sessionState;
    }

    public int getTuitionFee() {
        return tuitionFee.getValue();
    }

    public String getFeeType() {
        return feeType.getValue();
    }
}
