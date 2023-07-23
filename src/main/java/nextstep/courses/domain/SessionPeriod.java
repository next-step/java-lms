package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

    private static final String DATE_ERROR_MESSAGE = "시작일은 종료일 보다 늦을 수 없습니다.";

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public SessionPeriod(LocalDateTime startAt, LocalDateTime endAt) {
        validDate(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validDate(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException(DATE_ERROR_MESSAGE);
        }
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    @Override
    public String toString() {
        return "SessionPeriod{" +
                "startAt=" + startAt +
                ", endAt=" + endAt +
                '}';
    }

}
