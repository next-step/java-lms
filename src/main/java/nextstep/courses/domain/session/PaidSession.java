package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession extends Session {

    private final Long fee;
    private final int maxEnrollments;

    public PaidSession(Long id, SessionBody sessionBody, SessionEnrollment sessionEnrollment, Long fee, int maxEnrollments) {
        super(id, sessionBody, sessionEnrollment);

        this.fee = fee;
        this.maxEnrollments = maxEnrollments;
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        validateSessionStatus();
        validateDuplicateEnrollment(nsUser);
        validatePaymentAmount(payment);
        validateNumberOfEnrollment();

        sessionEnrollment.enrollUser(nsUser);
    }

    private void validatePaymentAmount(Payment payment) {
        if (isPaymentMismatched(payment)) {
            throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
        }
    }

    private boolean isPaymentMismatched(Payment payment) {
        return !fee.equals(payment.getAmount());
    }

    private void validateNumberOfEnrollment() {
        if (isEnrollmentFull()) {
            throw new IllegalStateException("수강 인원이 초과되었습니다.");
        }
    }

    private boolean isEnrollmentFull() {
        return sessionEnrollment.isEnrollmentFull(maxEnrollments);
    }

}
