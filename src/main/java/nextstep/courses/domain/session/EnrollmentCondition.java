package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

/**
 * 개인별 수강 신청 가능 조건 값을 관리하는 도메인
 */
public class EnrollmentCondition {
    private final NsUser user;
    private final Payment payment;

    public EnrollmentCondition(NsUser user, Payment payment) {
        if (!paidBySameUser(user, payment)) {
            throw new IllegalArgumentException("수강 신청자와 결제자 정보가 불일치합니다.");
        }
        this.user = user;
        this.payment = payment;
    }

    private boolean paidBySameUser(NsUser user, Payment payment) {
        return payment.isPaidBy(user.getId());
    }

    public NsUser getUser() {
        return user;
    }

    public boolean hasPaid(Long sessionId, long price) {
        return payment.isPaidFor(sessionId) && payment.isSameAmount(price);
    }
}
