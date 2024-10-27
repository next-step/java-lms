package nextstep.session;

import java.time.LocalDateTime;

public class DateRange {

    private static final String DATE_MESSAGE = "종료일이 시작일보다 빠릅니다.";

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public DateRange(LocalDateTime startDate, LocalDateTime endDate) {
        confirmDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void confirmDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(DATE_MESSAGE);
        }
    }
}
