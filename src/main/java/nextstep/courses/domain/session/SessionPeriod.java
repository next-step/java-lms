package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {
    private LocalDate startDate;

    private LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isEndDateBeforeNow() {
        return endDate.isBefore(LocalDate.now());
    }

    public void checkRecruit() {
        if (isEndDateBeforeNow()) {
            throw new IllegalStateException("종료된 강의는 모집할 수 없습니다.");
        }
    }
}

