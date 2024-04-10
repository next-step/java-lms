package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionPeriod {
    public static final String INVALID_SESSION_PERIOD = "강의 종료 시간이 강의 시작 시간보다 앞설 수 없습니다.";

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static SessionPeriod of(LocalDateTime startDate, LocalDateTime endDate) {
        if (!isValidTimeRange(startDate, endDate)) {
            throw new IllegalArgumentException(INVALID_SESSION_PERIOD);
        }

        return new SessionPeriod(startDate, endDate);
    }

    private static boolean isValidTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
        return endDate.isAfter(startDate);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

}
