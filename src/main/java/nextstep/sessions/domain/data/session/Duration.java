package nextstep.sessions.domain.data.session;

import java.time.LocalDateTime;

import nextstep.sessions.domain.exception.SessionsException;

public class Duration {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Duration(LocalDateTime startDate, LocalDateTime endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (!isValidDate(startDate, endDate)) {
            throw new SessionsException("시작일은 종료일보다 빨라야 합니다.");
        }
    }

    private static boolean isValidDate(LocalDateTime startDate, LocalDateTime endDate) {
        return endDate.isAfter(startDate);
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }
}
