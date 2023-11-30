package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public abstract class Session {
    private SessionInfo sessionInfo;
    private SessionStatus status;
    private int maxStudentLimit;
    private int currentStudentCount;
    private SessionDate sessionDate;

    protected Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate));
    }

    protected Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, 0), new SessionDate(startDate, endDate));
    }

    protected Session(final SessionInfo sessionInfo, final SessionDate sessionDate) {
        validateSession(sessionInfo, sessionDate);

        this.sessionInfo = sessionInfo;
        this.sessionDate = sessionDate;
        this.status = SessionStatus.READY;
        this.maxStudentLimit = 15;
        this.currentStudentCount = 0;
    }

    private void validateSession(final SessionInfo sessionInfo, final SessionDate sessionDate) {
        Assert.notNull(sessionInfo, "session info cannot be null");
        Assert.notNull(sessionDate, "session date cannot be null");
    }

    protected SessionStatus getStatus() {
        return this.status;
    }

    protected int getMaxStudentLimit() {
        return this.maxStudentLimit;
    }

    protected int getCurrentStudentCount() {
        return this.currentStudentCount;
    }

    protected long getPrice() {
        return this.sessionInfo.getPrice();
    }

    protected boolean isNotRecruiting() {
        return this.status != SessionStatus.RECRUITING;
    }

    protected void ready() {
        this.status = SessionStatus.READY;
    }

    protected void recruit() {
        this.status = SessionStatus.RECRUITING;
    }

    protected void close() {
        this.status = SessionStatus.CLOSED;
    }

    protected boolean isReachedMaxStudentLimit() {
        return getCurrentStudentCount() >= getMaxStudentLimit();
    }

    protected void enrollStudent() {
        this.currentStudentCount++;
    }

    public abstract void enroll(Payment payment);
}
