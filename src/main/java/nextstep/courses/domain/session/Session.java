package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public abstract class Session {
    private String title;
    private double price;
    private SessionStatus status;
    private int maxStudentLimit;
    private int currentStudentCount;
    private SessionDate sessionDate;

    protected Session(final String title, final double price, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(title, price, new SessionDate(startDate, endDate));
    }

    protected Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(title, 0, new SessionDate(startDate, endDate));
    }

    protected Session(final String title, final double price, final SessionDate sessionDate) {
        validateSession(title, price, sessionDate);

        this.title = title;
        this.price = price;
        this.sessionDate = sessionDate;
        this.status = SessionStatus.READY;
        this.maxStudentLimit = 15;
        this.currentStudentCount = 0;
    }

    private void validateSession(final String title, final double price, final SessionDate sessionDate) {
        Assert.hasText(title, "title cannot be blank");
        Assert.isTrue(price >= 0, "price cannot be negative");
        Assert.notNull(sessionDate, "session date cannot be null");
    }

    protected String getTitle() {
        return this.title;
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

    protected double getPrice() {
        return this.price;
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
