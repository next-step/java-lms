package nextstep.courses.domain.vo;

import java.time.LocalDate;

public class SessionPeriod {

    private LocalDate startDate;

    private LocalDate endDate;


    public SessionPeriod(LocalDate startDate,
                         LocalDate endDate) {
        validatePeriod(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validatePeriod(LocalDate startDate,
                                LocalDate endDate) {
        if (startDate.isAfter(endDate)){
            throw new IllegalArgumentException("시작일은 종료일보다 뒤이어야 합니다.");
        }
        if (startDate.equals(endDate)) {
            throw new IllegalArgumentException("시작일과 종료일은 같을 수 없습니다.");
        }
    }
}
