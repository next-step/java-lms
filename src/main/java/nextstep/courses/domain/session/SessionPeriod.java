package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionPeriod {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("강의 시작일은 강의 종료일 이전이어야 합니다.");
        }
    }
}
