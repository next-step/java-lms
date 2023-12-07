package nextstep.sessions.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime startedDate;
    private final LocalDateTime endedDate;

    public SessionPeriod(LocalDateTime startedDate, LocalDateTime endedDate) {
        validateDate(startedDate, endedDate);
        this.startedDate = startedDate;
        this.endedDate = endedDate;
    }

    private void validateDate(LocalDateTime startedDate, LocalDateTime endedDate) {
        if (startedDate.isAfter(endedDate)) {
            throw new IllegalArgumentException("강의 시작일은 종료일을 넘을 수 없습니다.");
        }
    }
}
