package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionDate {
    private static final String START_DATE_CANT_LATE_THEN_END_DATE = "강의 시작일은 종료일 이전이어야 합니다.";

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private SessionDate(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static SessionDate of(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(START_DATE_CANT_LATE_THEN_END_DATE);
        }
        return new SessionDate(startDate, endDate);
    }
}
