package nextstep.courses.domain.strategy;

import nextstep.courses.domain.FreeStudents;
import nextstep.courses.domain.Period;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.Thumbnail;
import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession implements Session {
    private final Period period;
    private final Thumbnail thumbnail;
    private final FreeStudents students;
    private final SessionStatus status;

    public FreeSession(Period period,
                       Thumbnail thumbnail,
                       FreeStudents students,
                       SessionStatus status) {
        this.period = period;
        this.thumbnail = thumbnail;
        this.students = students;
        this.status = status;
    }

    @Override
    public boolean isSupport(SessionType sessionType) {
        return SessionType.FREE == sessionType;
    }

    @Override
    public void apply(Payment payment,
                      NsUser student) {
        status.validateApply();
        students.add(student);
    }
}
