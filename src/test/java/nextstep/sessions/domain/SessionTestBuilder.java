package nextstep.sessions.domain;

import java.time.LocalDate;

class SessionTestBuilder {

    private Long id = 1L;
    private LocalDate startDate = PeriodTest.START_DATE;
    private LocalDate endDate = PeriodTest.END_DATE;
    private SessionStatus status = SessionStatus.PREPARING;
    private boolean paid = false;
    private Integer maxCapacity = Integer.MAX_VALUE;
    private boolean unlimited = true;
    private int fee = 0;
    private int enrollCount = 0;
    private SessionImage image = SessionImageTest.IMAGE;

    public SessionTestBuilder paid(Integer maxCapacity, int fee) {
        this.paid = true;
        this.maxCapacity = maxCapacity;
        this.unlimited = false;
        this.fee = fee;
        return this;
    }

    public SessionTestBuilder free() {
        this.paid = false;
        this.maxCapacity = Integer.MAX_VALUE;
        this.fee = 0;
        return this;
    }

    public SessionTestBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SessionTestBuilder maxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
        return this;
    }

    public SessionTestBuilder fee(int fee) {
        this.fee = fee;
        return this;
    }

    public SessionTestBuilder enrollCount(int count) {
        this.enrollCount = count;
        return this;
    }

    public Session build() {
        if (paid) {
            return Session.paidLimited(id, startDate, endDate, fee, maxCapacity, image);
        }
        return Session.freeUnlimited(id, startDate, endDate, image);
    }

}
