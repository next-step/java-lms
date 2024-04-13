package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class PaidSession extends Session {

    public PaidSession(long id, long maximumNumberOfStudent, long amount, LocalDateTime startedAt, LocalDateTime endedAt, SessionCoverImage coverImage, SessionType type) {
        super(id, amount, maximumNumberOfStudent, startedAt, endedAt, coverImage, type);
    }

    @Override
    public void enroll(NsUser user, Payment payment) {
        validateRecruiting();
        validateSameAmount(payment);
        validateLessEqualThenMaximumNumber();
        students.add(user);
    }

    private void validateSameAmount(Payment payment) {
        if (!payment.isSameAmount(amount)) {
            throw new IllegalArgumentException("결제 금액과 수강료가 일치하지 않습니다.");
        }
    }

    private void validateLessEqualThenMaximumNumber() {
        if (students.size() >= maximumNumberOfStudent) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }
    }
}
