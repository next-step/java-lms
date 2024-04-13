package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Fee {

    private Long amount;

    public Fee(Long amount, SessionType type) {
        validateFee(amount, type);
        this.amount = amount;
    }

    private void validateFee(Long amount, SessionType type) {
        if (type == SessionType.FREE && amount > 0) {
            throw new IllegalArgumentException("무료 강의는 강의 금액이 있을 수 없습니다.");
        }

        if (type == SessionType.PAID && amount <= 0) {
            throw new IllegalArgumentException("유료 강의는 강의 금액이 0원 이상입니다.");
        }
    }

    public Long getAmount() {
        return amount;
    }

    public void enroll(Long userId, Long sessionId) {
        // 유료 강의 수강 신청 : payment amount가 수강료와 일치할 때 수강 신청이 가능하다.
        // Payment id, sessionId, nsUserId, amount가 필요함
        Payment payment = new Payment();
        if (!payment.isEqualAmount(this)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }
}
