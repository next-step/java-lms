package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaidSession extends Session {

    public PaidSession(long id,
                       long courseId,
                       long maximumNumberOfStudent,
                       long amount) {
        this(id, courseId, new ArrayList<>(), maximumNumberOfStudent, amount, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    public PaidSession(long id,
                       long courseId,
                       List<Long> coverImageIds,
                       long maximumNumberOfStudent,
                       long amount) {
        this(id, courseId, coverImageIds, maximumNumberOfStudent, amount, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    public PaidSession(long id,
                       long courseId,
                       long maximumNumberOfStudent,
                       long amount,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt) {
        this(id, courseId, new ArrayList<>(), amount, maximumNumberOfStudent, startedAt, endedAt);
    }

    public PaidSession(long id,
                       long courseId,
                       List<Long> coverImageIds,
                       long maximumNumberOfStudent,
                       long amount,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt) {
        super(id, courseId, coverImageIds, amount, maximumNumberOfStudent, startedAt, endedAt, SessionType.PAID);
    }

    @Override
    public void enroll(NsUser user, Payment payment) {
        validateRecruiting();
        validateSameAmount(payment);
        validateLessEqualThenMaximumNumber();
        this.enrollments.add(new Enrollment(this.id, user.getId()));
    }

    private void validateSameAmount(Payment payment) {
        if (!payment.isSameAmount(amount)) {
            throw new IllegalArgumentException("결제 금액과 수강료가 일치하지 않습니다.");
        }
    }

    private void validateLessEqualThenMaximumNumber() {
        if (enrollments.size() >= maximumNumberOfStudent) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }
    }
}
