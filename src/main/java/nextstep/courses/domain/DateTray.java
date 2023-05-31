package nextstep.courses.domain;

import java.time.LocalDate;

public class DateTray {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateTray(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

        checkValidate();
    }

    private void checkValidate() {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        }
    }

}
