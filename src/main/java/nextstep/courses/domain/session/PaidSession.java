package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession extends Session {

    private final long fee;
    private final int maxEnrollments;

    public PaidSession(long id, long courseId, SessionBody sessionBody, SessionEnrollment sessionEnrollment, long fee, int maxEnrollments) {
        super(id, courseId, sessionBody, sessionEnrollment);

        validatePaidSession(fee, maxEnrollments);

        this.fee = fee;
        this.maxEnrollments = maxEnrollments;
    }

    private void validatePaidSession(long fee, int maxEnrollments) {
        validateFee(fee);
        validateMaxEnrollments(maxEnrollments);
    }

    private void validateFee(long fee) {
        if (fee <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강료가 0원을 초과하여야 합니다.");
        }
    }

    private void validateMaxEnrollments(int maxEnrollments) {
        if (maxEnrollments <= 0) {
            throw new IllegalArgumentException("유료 강의는 최대 수강 인원 1명 이상이어야 합니다.");
        }
    }

    @Override
    public void enroll(Student student, Payment payment) {
        validateSessionStatus();
        validatePaymentAmount(payment);
        validateNumberOfEnrollment();

        sessionEnrollment.enrollStudent(student);
    }

    private void validatePaymentAmount(Payment payment) {
        if (isPaymentMismatched(payment)) {
            throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
        }
    }

    private boolean isPaymentMismatched(Payment payment) {
        return payment.isPaymentMismatched(fee);
    }

    private void validateNumberOfEnrollment() {
        if (isEnrollmentFull()) {
            throw new IllegalStateException("수강 인원이 초과되었습니다.");
        }
    }

    private boolean isEnrollmentFull() {
        return sessionEnrollment.isEnrollmentFull(maxEnrollments);
    }

    @Override
    public long getFee() {
        return fee;
    }

    @Override
    public int getMaxEnrollments() {
        return maxEnrollments;
    }

}
