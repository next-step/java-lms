package nextstep.sessions.domain.enrollment;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.SessionPricing;
import nextstep.users.domain.NsUser;

public class Enrollment {

    static final String ERROR_USER_PAYMENT_MISMATCH = "결제한 사용자와 신청자가 일치하지 않습니다";

    private final NsUser user;
    private final Payment payment;
    private final LocalDateTime enrolledAt;

    public Enrollment(NsUser user, Payment payment) {
        validateUserPayMatch(user, payment);
        this.user = user;
        this.payment = payment;
        this.enrolledAt = LocalDateTime.now();
    }

    public Payment payment() {
        return payment;
    }

    public boolean canPayFor(SessionPricing pricing) {
        return !pricing.isPaid() || payment.isPaidFor(pricing.fee());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Enrollment that = (Enrollment) o;
        return Objects.equals(user, that.user) && Objects.equals(payment, that.payment)
                && Objects.equals(enrolledAt, that.enrolledAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, payment, enrolledAt);
    }

    private static void validateUserPayMatch(NsUser user, Payment payment) {
        if (payment.isPaidBy(user)) {
            return;
        }
        throw new IllegalArgumentException(ERROR_USER_PAYMENT_MISMATCH);
    }

}
