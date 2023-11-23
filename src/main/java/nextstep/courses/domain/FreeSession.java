package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class FreeSession implements Session {
    private final Period period;
    private final Thumbnail thumbnail;
    private final Students students;

    public FreeSession(Period period,
                       Thumbnail thumbnail,
                       Students students) {
        this.period = period;
        this.thumbnail = thumbnail;
        this.students = students;
    }

    @Override
    public boolean isSupport(SessionType sessionType) {
        return SessionType.FREE == sessionType;
    }

    @Override
    public void apply(Payment payment) {
        students.add();
    }
}
