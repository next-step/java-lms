package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private final Free free;
    private final Amount maxCount;
    private final Money tuition;
    private final SessionStatus status;
    private ImageFile image;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Session() {
        this(true, 0, 0, SessionStatus.READY);
    }

    public Session(boolean isFree, int maxCount, int tuition) {
        this(isFree, maxCount, tuition, SessionStatus.READY);
    }

    public Session(boolean isFree, int maxCount, int tuition, SessionStatus status) {
        this(isFree, maxCount, tuition, status, LocalDate.now(), LocalDate.now().plusDays(60));
    }

    public Session(boolean isFree, int maxCount, int tuition, SessionStatus status, LocalDate startDate, LocalDate endDate) {
        this(new Free(isFree), new Amount(maxCount), new Money(tuition), status, startDate, endDate);
    }

    public Session(Free free, Amount maxCount, Money tuition, SessionStatus status, LocalDate startDate, LocalDate endDate) {
        this.free = free;
        this.maxCount = maxCount;
        this.tuition = tuition;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isFrees() {
        return free.isFree();
    }

    public boolean hasMax() {
        if (maxCount.isNoLimit()) {
            return false;
        }
        return true;
    }

    public boolean isLeft(Amount count) {
        return maxCount.isSmaller(count);
    }

    public boolean canListen(Money submit) {
        return tuition.equals(submit);
    }

    public boolean canRegister() {
        return this.status.equals(SessionStatus.RECRUITING);
    }
}
