package nextstep.sessions.domain;

import java.time.LocalDateTime;
import nextstep.payments.domain.Payment;
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

    private static void validateUserPayMatch(NsUser user, Payment payment) {
        if (payment.isPaidBy(user)) {
            return;
        }
        throw new IllegalArgumentException(ERROR_USER_PAYMENT_MISMATCH);
    }

}
