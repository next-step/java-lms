package nextstep.sessions.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

    private LocalDateTime startedAt;

    private LocalDateTime endAt;

    public SessionPeriod(final LocalDateTime startedAt, final LocalDateTime endAt) {
        validateSessionDates(startedAt, endAt);
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    public void validateStartedAt() {
        if (startedAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("시작일이 지난 강의는 수강 신청할 수 없습니다.");
        }
    }

    private void validateSessionDates(final LocalDateTime startedAt, final LocalDateTime endAt) {
        if (endAt.isBefore(startedAt)) {
            throw new IllegalArgumentException("종료일이 시작일보다 빠를 수 없습니다.");
        }
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
