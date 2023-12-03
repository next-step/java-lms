package nextstep.courses.domain;

import nextstep.courses.exception.NotCorrectPeriodException;

import java.time.LocalDateTime;

public class Period {

    private final LocalDateTime startAt;

    private final LocalDateTime endAt;

    public Period(LocalDateTime startAt, LocalDateTime endAt) {
        validateStartAt(startAt, endAt);
        validateEndAt(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateEndAt(LocalDateTime startAt, LocalDateTime endAt) {
        if (endAt.isBefore(startAt)) {
            throw new NotCorrectPeriodException();
        }
    }

    private void validateStartAt(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new NotCorrectPeriodException();
        }
    }
}
