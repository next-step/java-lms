package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;
    private static final String WRONG_DATE = "종료일은 시작일보다 빠를 수 없습니다.";

    public SessionPeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        validateDate(fromDate, toDate);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public void validateDate(LocalDateTime fromDate, LocalDateTime toDate) {
        if (toDate.isBefore(fromDate)) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }

}
