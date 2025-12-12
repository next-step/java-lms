package nextstep.sessions.domain;

import java.time.LocalDate;

class SessionTestBuilder {

    private LocalDate startDate = SessionTest.START_DATE;
    private LocalDate endDate = SessionTest.END_DATE;
    private boolean paid = false;
    private Integer maxCapacity = null;
    private int fee = 0;
    private int enrollCount = 0;
    private SessionImage image = SessionImageTest.IMAGE;

    public SessionTestBuilder paid(Integer maxCapacity, int fee) {
        this.paid = true;
        this.maxCapacity = maxCapacity;
        this.fee = fee;
        return this;
    }

    public SessionTestBuilder free() {
        this.paid = false;
        this.maxCapacity = null;
        this.fee = 0;
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
            return new Session(startDate, endDate, true, maxCapacity, fee, enrollCount, image);
        }
        return new Session(startDate, endDate, false, maxCapacity, fee, image);
    }
}
