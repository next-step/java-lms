package nextstep.courses.domain;

import java.time.LocalDate;

/**
 * 강의 엔터티 - 가변 객체
 */
public class Session {
    private final Long id;
    private final Period period;
    private SessionStatus status;
    private final Integer price;

    public Session(Long id, Period period) {
        this(id, period, SessionStatus.PREPARING);
    }

    public Session(Long id, Period period, SessionStatus status) {
        this(id, period, status, 0);
    }

    public Session(Long id, Period period, SessionStatus status, int price) {
        this.id = id;
        this.period = period;
        this.status = status;
        this.price = price;
    }

    public boolean canRegister() {
        return status.equals(SessionStatus.OPEN);
    }

    public LocalDate startAt() {
        return period.startAt();
    }

    public LocalDate endAt() {
        return period.endAt();
    }

    public boolean isFree() {
        return price == 0;
    }
}
