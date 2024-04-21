package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import nextstep.courses.error.exception.EndDateBeforeStartDateException;
import nextstep.courses.error.exception.NotExistTimeException;

public class ValidityPeriod {

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public ValidityPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null){
            throw new NotExistTimeException(startDate, endDate);
        }

        if (endDate.isBefore(startDate)) {
            throw new EndDateBeforeStartDateException(startDate, endDate);
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
