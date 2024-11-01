package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession extends Session {

    private Long fee;
    private int maxEnrollments;

    public PaidSession(Long id, String title, SessionPeriod period, CoverImage coverImage, Long fee, int maxEnrollments) {
        super(id, title, period, coverImage);

        this.fee = fee;
        this.maxEnrollments = maxEnrollments;
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        validateSessionStatus();
        validateDuplicateEnrollment(nsUser);
        validatePaymentAmount(payment);
        validateNumberOfEnrollment();

        enrolledUsers.add(nsUser);
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
        return enrolledUsers.size() >= maxEnrollments;
    }

}
