package nextstep.courses.domain.session;

import java.time.LocalDate;

public class Period {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validateStartDate(startDate);
        validateEndDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isEqualStartDateAndToday() {
        return startDate.isEqual(LocalDate.now());
    }

    private void validateStartDate(LocalDate startDate) {
        if (isBeforeToday(startDate)) {
            throw new IllegalArgumentException("강의 시작일은 오늘 날짜 이후로 설정할 수 있습니다.");
        }
    }

    private void validateEndDate(LocalDate startDate, LocalDate endDate) {
        if (!xDateIsBeforeYDate(startDate, endDate)) {
            throw new IllegalArgumentException("강의 종료일은 강의 시작일 이후로 설정할 수 있습니다.");
        }
    }

    private boolean isBeforeToday(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    private boolean xDateIsBeforeYDate(LocalDate startDate, LocalDate endDate) {
        return !startDate.isAfter(endDate);
    }
}
