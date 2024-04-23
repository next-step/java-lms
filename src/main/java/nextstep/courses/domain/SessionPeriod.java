package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

    public static final String INVALID_SESSION_PERIOD = "강의 종료 시간이 강의 시작보다 빠를 수 없습ㄴ디ㅏ.";

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static SessionPeriod of(LocalDateTime startDate, LocalDateTime endDate) {
        if (!isValidSessionPeriod(startDate, endDate)) {
            throw new IllegalArgumentException(INVALID_SESSION_PERIOD);
        }
        return new SessionPeriod(startDate, endDate);
    }

    private static boolean isValidSessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return endDate.isAfter(startDate);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
