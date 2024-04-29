package nextstep.sessions.domain;

import java.time.LocalDate;

public class SessionDate {

    private final StartedAt startedAt;
    private final EndedAt endedAt;

    public SessionDate(StartedAt startedAt, EndedAt endedAt) {
        validateSessionDate(startedAt.getStartedAt(), endedAt.getEndedAt());
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    private static void validateSessionDate(final LocalDate startedAt, final LocalDate endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("강의 종료일보다 강의 시작일이 늦을 수 없습니다.");
        }
    }

    public LocalDate getStartedAt() {
        return startedAt.getStartedAt();
    }

    public LocalDate getEndedAt() {
        return endedAt.getEndedAt();
    }
}
