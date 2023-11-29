package nextstep.courses.domain;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public abstract class Session {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionStatus status;

    protected Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        validateSession(title, startDate, endDate);

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = SessionStatus.READY;
    }

    private void validateSession(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        Assert.hasText(title, "title cannot be blank");
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
}
