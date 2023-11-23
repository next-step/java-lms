package nextstep.courses.domain;

import nextstep.courses.IncorrectAmountException;
import nextstep.courses.SessionDeadLineException;
import nextstep.payments.domain.Payment;

public class PaidSession implements Session {
    private final Period period;
    private final Thumbnail thumbnail;
    private final Students limitStudents;
    private final Students students;
    private final long amount;

    public PaidSession(Period period,
                       Thumbnail thumbnail,
                       Students limitStudents,
                       Students students,
                       long amount) {
        this.limitStudents = limitStudents;
        this.students = students;
        this.period = period;
        this.thumbnail = thumbnail;
        this.amount = amount;
    }

    @Override
    public boolean isSupport(SessionType sessionType) {
        return SessionType.PAID == sessionType;
    }

    @Override
    public void apply(Payment payment) {
        if (isDeadLine()) {
            throw new SessionDeadLineException("수강 신청이 마감 되었습니다.");
        }

        if (!payment.isSameAmount(amount)) {
            throw new IncorrectAmountException("결제 금액과 강의 금액이 다릅니다.");
        }

        students.add();
    }

    private boolean isDeadLine() {
        return limitStudents.equals(students);
    }
}
