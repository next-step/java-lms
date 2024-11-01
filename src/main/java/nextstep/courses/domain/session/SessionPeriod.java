package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionPeriod {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        validateSessionPeriod(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static SessionPeriod of(LocalDateTime startDate, LocalDateTime endDate) {
        return new SessionPeriod(startDate, endDate);
    }

    private void validateSessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (isStartDateAfter(startDate, endDate)) {
            throw new IllegalArgumentException("시작 날짜는 종료 날짜보다 이후일 수 없습니다.");
        }
    }

    private boolean isStartDateAfter(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.isAfter(endDate);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

}
