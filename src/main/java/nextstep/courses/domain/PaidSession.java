package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class PaidSession extends Session {

    private final int amount;

    public PaidSession(int maximumNumberOfStudent, int amount, LocalDateTime startedAt, LocalDateTime endedAt) {
        super(maximumNumberOfStudent, startedAt, endedAt);
        this.amount = amount;
    }

    @Override
    public void registers(List<NsUser> users) {
        users.forEach(this::register);
    }

    @Override
    public void register(NsUser user) {
        this.register(user, Payment.findByUserId(user.getId()));
    }

    public void register(NsUser user, Payment payment) {
        validateLessEqualThenMaximumNumber();
        validateRecruiting();
        validateSameAmount(payment);
    }

    private void validateSameAmount(Payment payment) {
        if (!payment.isSameAmount(amount)) {
            throw new IllegalArgumentException("결제 금액과 수강료가 일치하지 않습니다.");
        }
    }

    private void validateRecruiting() {
        if (!SessionStatus.isRecruiting(status)) {
            throw new IllegalArgumentException("현재 모집 중인 강의가 아닙니다.");
        }
    }

    private void validateLessEqualThenMaximumNumber() {
        if (students.size() >= maximumNumberOfStudent) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }
    }
}
