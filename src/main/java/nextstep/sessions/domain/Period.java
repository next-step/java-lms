package nextstep.sessions.domain;

import java.time.LocalDate;

public class Period {

    private static final String ERROR_INVALID_DATE = "시작일이 종료일보다 빨라야 합니다";

    private final LocalDate startDate;

    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
        }
    }

}
