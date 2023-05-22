package nextstep.courses.domain;

import nextstep.courses.NotIncludePeriodException;

import java.time.LocalDate;

import static nextstep.courses.NotIncludePeriodException.MESSAGE;

public class SessionPeriod {

    private LocalDate startedAt;

    private LocalDate endedAt;

    public SessionPeriod(LocalDate startedAt, LocalDate endedAt) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public void includeCurrentDate(LocalDate nowDate) {
        if (nowDate.isBefore(startedAt) || nowDate.isAfter(endedAt)) {
            throw new NotIncludePeriodException(MESSAGE);
        }
    }
}
