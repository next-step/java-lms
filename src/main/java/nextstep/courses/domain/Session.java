package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public abstract class Session {
    private String title;
    private double price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionStatus status;
    private int maxStudentLimit;
    private int currentStudentCount;

    protected Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(title, 0, startDate, endDate);
    }

    protected Session(final String title, final double price, final LocalDateTime startDate, final LocalDateTime endDate) {
        validateSession(title, price, startDate, endDate);

        this.title = title;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = SessionStatus.READY;
        this.maxStudentLimit = 15;
        this.currentStudentCount = 0;
    }

    private void validateSession(final String title, final double price, final LocalDateTime startDate, final LocalDateTime endDate) {
        Assert.hasText(title, "title cannot be blank");
        Assert.isTrue(price >= 0, "price cannot be negative");
        Assert.notNull(startDate, "start date cannot be null");
        Assert.notNull(endDate, "end date cannot be null");

        Assert.isTrue(startDate.isBefore(endDate), "start date cannot be after end date");
    }

    protected String getTitle() {
        return this.title;
    }

    protected LocalDateTime getStartDate() {
        return this.startDate;
    }

    protected LocalDateTime getEndDate() {
        return this.endDate;
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
