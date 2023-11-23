package nextstep.courses.domain;

import nextstep.courses.SessionDeadLineException;
import nextstep.payments.domain.Payment;

public class PaidSession implements Session {

    private final SessionType sessionType;
    private final Period period;
    private final Thumbnail thumbnail;
    private final Students limitStudents;
    private final Students students;

    public PaidSession(Period period,
                       Thumbnail thumbnail,
                       Students limitStudents,
                       Students students) {
        this.limitStudents = limitStudents;
        this.students = students;
        this.sessionType = SessionType.PAID;
        this.period = period;
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean isSupport(SessionType sessionType) {
        return this.sessionType == sessionType;
    }

    @Override
    public void apply(Payment payment) {
        if (isDeadLine()) {
            throw new SessionDeadLineException("수강 신청이 마감 되었습니다.");
        }

        students.add();
    }

    private boolean isDeadLine() {
        return limitStudents.equals(students);
    }
}
