package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidSession implements Session {

    private final SessionType sessionType;
    private final Period period;
    private final Thumbnail thumbnail;

    public PaidSession(Period period,
                       Thumbnail thumbnail) {
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

    }
}
