package nextstep.courses.domain.strategy;

import nextstep.courses.domain.*;
import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
import nextstep.courses.exception.IncorrectAmountException;
import nextstep.courses.exception.SessionDeadLineException;
import nextstep.payments.domain.Payment;

public class PaidSession implements Session {
    private final Period period;
    private final Thumbnail thumbnail;
    private final Students limitStudents;
    private final Students students;
    private final Amount amount;
    private final SessionStatus status;

    public PaidSession(Period period,
                       Thumbnail thumbnail,
                       Students limitStudents,
                       Students students,
                       Amount amount,
                       SessionStatus status) {
        this.limitStudents = limitStudents;
        this.students = students;
        this.period = period;
        this.thumbnail = thumbnail;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public boolean isSupport(SessionType sessionType) {
        return SessionType.PAID == sessionType;
    }

    @Override
    public void apply(Payment payment) {
        validateDeadLine();
        validateAmount(payment);
        validateStatus();

        students.add();
    }

    private void validateDeadLine() {
        if (isDeadLine()) {
            throw new SessionDeadLineException("수강 신청이 마감 되었습니다.");
        }
    }

    private boolean isDeadLine() {
        return limitStudents.equals(students);
    }

    private void validateAmount(Payment payment) {
        if (!amount.isSameAmount(payment)) {
            throw new IncorrectAmountException("결제 금액과 강의 금액이 다릅니다.");
        }
    }

    private void validateStatus() {
        status.validateApply();
    }

}
