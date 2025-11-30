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
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 이후여야 한다");
        }
    }

}
