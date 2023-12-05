package nextstep.courses.domain.session;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class SessionDate {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionDate(final LocalDateTime startDate, final LocalDateTime endDate) {
        validate(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate(final LocalDateTime startDate, final LocalDateTime endDate) {
        Assert.notNull(startDate, "start date cannot be null");
        Assert.notNull(endDate, "end date cannot be null");

        Assert.isTrue(startDate.isBefore(endDate), "start date cannot be after end date");
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }
}
