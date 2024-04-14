package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionDate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        assertValidDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void assertValidDate(LocalDate startDate, LocalDate endDate) {
        String invalidEndDateMessage = "강의 끝나는 날짜가 시작 날짜보다 더 빠릅니다.";
        String invalidStartDateMessage = "강의 시작 날짜가 현재 날짜보다 바릅니다.";

        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException(invalidEndDateMessage);
        }

        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(invalidStartDateMessage);
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
