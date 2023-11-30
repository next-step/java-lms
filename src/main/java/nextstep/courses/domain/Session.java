package nextstep.courses.domain;

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

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public SessionStatus getStatus() {
        return this.status;
    }

    public int getMaxStudentLimit() {
        return this.maxStudentLimit;
    }

    public int getCurrentStudentCount() {
        return this.currentStudentCount;
    }

    public double getPrice() {
        return this.price;
    }
}
